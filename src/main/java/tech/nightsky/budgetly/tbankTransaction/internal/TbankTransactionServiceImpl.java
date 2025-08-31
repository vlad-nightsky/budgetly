package tech.nightsky.budgetly.tbankTransaction.internal;

import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import tech.nightsky.budgetly.category.CategoryService;
import tech.nightsky.budgetly.category.CategorySummary;
import tech.nightsky.budgetly.core.parser.CsvParser;
import tech.nightsky.budgetly.core.util.ParserUtil;
import tech.nightsky.budgetly.tbankTransaction.TbankTransactionService;
import tech.nightsky.budgetly.tbankTransaction.TbankTransactionSummary;
import tech.nightsky.budgetly.transaction.TransactionRequest;
import tech.nightsky.budgetly.transaction.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TbankTransactionServiceImpl implements TbankTransactionService {
    private final TbankTransactionRepository repository;
    private final TbankTransactionMapper mapper;
    private final FilterResolver filterResolver;
    private final CategoryResolver categoryResolver;
    private final CategoryService categoryService;

    @Override
    public List<TbankTransactionSummary> parseCsv(MultipartFile file, Long importId) {
        return CsvParser.builder()
                .file(file)
                .delimiter(";")
                .build()
                .parse(this::parse)
                .stream()
                .map(tr -> tr.finishParse(importId))
                .map(mapper::toSummary)
                .toList();
    }

    @Override
    public Boolean trySave(TbankTransactionSummary summary) {
        var tbankTransaction = mapper.toEntity(summary);
        final var rowHash = tbankTransaction.getRowHash();
        if (repository.existsByRowHash(rowHash)) {
            final var exisingTransactions = repository.findByRowHash(rowHash);
            if (exisingTransactions.stream().anyMatch(t -> t.equals(mapper.toEntity(summary)))) {
                return false;
            }
        }
        repository.save(tbankTransaction);
        return true;
    }

    @Override
    public CategorySummary findCategoryByTbankTransaction(TbankTransactionSummary transaction, Long accountId) {
        final var categoryCode = categoryResolver.findCategoryCodesBy(transaction);
        return categoryService.getCategoryByCodeAndAccountId(categoryCode, accountId);
    }

    @Override
    public TransactionRequest toTransaction(TbankTransactionSummary tbankTransaction, Long accountId) {
        return TransactionRequest.builder()
                .accountId(accountId)
                .amount(
                        tbankTransaction.operationAmount() != null
                                ? tbankTransaction.operationAmount().abs()
                                : new BigDecimal(0)
                ).categoryId(
                        findCategoryByTbankTransaction(tbankTransaction, accountId)
                                .id()
                ).type(typeBy(tbankTransaction))
                .description(tbankTransaction.description())
                .build();
    }

    @Override
    public boolean isFiltered(TbankTransactionSummary tbankTransaction) {
        return filterResolver.isFiltered(tbankTransaction);
    }

    @Override
    public void setFiltered(TbankTransactionSummary tbankTransaction) {
        TbankTransaction transaction = mapper.toEntity(tbankTransaction);
        transaction.filtered();
        repository.save(transaction);
    }

    @Override
    public void setParsed(TbankTransactionSummary tbankTransaction) {
        TbankTransaction transaction = mapper.toEntity(tbankTransaction);
        transaction.parsed();
        repository.save(transaction);
    }

    private TransactionType typeBy(TbankTransactionSummary tbankTransaction) {
        if (tbankTransaction.operationAmount().signum() >= 0) {
            return TransactionType.INCOME;
        } else {
            return TransactionType.EXPENSE;
        }
    }

    private TbankTransaction parse(CSVRecord csvRecord) {
        return TbankTransaction.builder()
                .operationDate(ParserUtil.parseDateTime(csvRecord.get("Дата операции")))
                .paymentDate(ParserUtil.parseDate(csvRecord.get("Дата платежа")))
                .cardNumber(csvRecord.get("Номер карты"))
                .status(csvRecord.get("Статус"))
                .operationAmount(ParserUtil.parseNumber(csvRecord.get("Сумма операции")))
                .operationCurrency(csvRecord.get("Валюта операции"))
                .paymentAmount(ParserUtil.parseNumber(csvRecord.get("Сумма платежа")))
                .paymentCurrency(csvRecord.get("Валюта платежа"))
                .cashback(ParserUtil.parseNumber(csvRecord.get("Кэшбэк")))
                .category(csvRecord.get("Категория"))
                .mcc(StringUtils.hasText(csvRecord.get("MCC"))
                        ? Integer.parseInt(csvRecord.get("MCC"))
                        : null)
                .description(csvRecord.get("Описание"))
                .bonuses(ParserUtil.parseNumber(csvRecord.get("Бонусы (включая кэшбэк)")))
                .investmentRounding(ParserUtil.parseNumber(csvRecord.get("Округление на инвесткопилку")))
                .roundedOperationAmount(ParserUtil.parseNumber((csvRecord.get("Сумма операции с округлением"))))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
