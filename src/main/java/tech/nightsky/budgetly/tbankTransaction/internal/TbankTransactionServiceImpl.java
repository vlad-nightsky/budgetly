package tech.nightsky.budgetly.tbankTransaction.internal;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tech.nightsky.budgetly.category.CategorySummary;
import tech.nightsky.budgetly.core.parser.CsvParser;
import tech.nightsky.budgetly.core.util.ParserUtil;
import tech.nightsky.budgetly.tbankTransaction.TbankTransactionService;
import tech.nightsky.budgetly.tbankTransaction.TbankTransactionSummary;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TbankTransactionServiceImpl implements TbankTransactionService {
    private final TbankTransactionRepository repository;
    private final TbankTransactionMapper mapper;

    @Override
    public List<TbankTransactionSummary> parseCsv(MultipartFile file, Long tbankImportId) {
        return CsvParser.builder()
                .file(file)
                .delimiter(";")
                .build()
                .parse(this::parce)
                .stream()
                .map(tr -> tr.finishParse(tbankImportId))
                .map(mapper::map)
                .toList();
    }

    @Override
    public Boolean trySave(TbankTransactionSummary summary) {
        var tbankTransaction = mapper.map(summary);
        val rowHash = tbankTransaction.getRowHash();
        if (repository.existsByRowHash(rowHash)) {
            val exisingTransactions = repository.findByRowHash(rowHash);
            if (exisingTransactions.stream().anyMatch(t -> t.equals(summary))) {
                return false;
            }
        }
        repository.save(tbankTransaction);
        return true;
    }

    @Override
    public CategorySummary findCategoryByTbankTransaction(TbankTransactionSummary transaction) {


        return null;
    }

    private TbankTransaction parce(CSVRecord csvRecord) {
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
                .mcc(Integer.parseInt(csvRecord.get("MCC")))
                .description(csvRecord.get("Описание"))
                .bonuses(ParserUtil.parseNumber(csvRecord.get("Бонусы (включая кэшбэк)")))
                .investmentRounding(ParserUtil.parseNumber(csvRecord.get("Округление на инвесткопилку")))
                .roundedOperationAmount(ParserUtil.parseNumber((csvRecord.get("Сумма операции с округлением"))))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
