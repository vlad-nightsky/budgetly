package tech.nightsky.budgetly.tbankImport.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tech.nightsky.budgetly.tbankImport.ImportService;
import tech.nightsky.budgetly.tbankImport.TbankImportSummary;
import tech.nightsky.budgetly.tbankTransaction.TbankTransactionService;
import tech.nightsky.budgetly.tbankTransaction.TbankTransactionSummary;
import tech.nightsky.budgetly.transaction.TransactionRequest;
import tech.nightsky.budgetly.transaction.TransactionService;
import tech.nightsky.budgetly.transaction.TransactionSummary;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImportServiceImpl implements ImportService {
    private final TbankImportRepository importRepository;
    private final TbankImportMapper mapper;
    private final TbankTransactionService tbankTransactionService;
    private final TransactionService transactionService;

    @Override
    @Transactional
    public TbankImportSummary importCsv(MultipartFile file, Long accountId) {
        //при сохранении объекту присвоен ID
        TbankImport tbankImport = importRepository.save(TbankImport.init());
        List<TbankTransactionSummary> rowTransactions = tbankTransactionService.parseCsv(file, tbankImport.getId());
        int skipped = 0, saved = 0;
        var newUniqueTransaction = new ArrayList<TbankTransactionSummary>();

        for (TbankTransactionSummary transaction : rowTransactions) {
            Boolean isSaved = tbankTransactionService.trySave(transaction);
            if (isSaved) {
                saved++;
                newUniqueTransaction.add(transaction);
            } else {
                skipped++;
            }
        }

        List<TransactionSummary> newClearTransaction = newUniqueTransaction.stream()
                .map(tbankTransaction -> {
                    if (tbankTransactionService.isFiltered(tbankTransaction)) {
                        tbankTransaction = tbankTransactionService.setFiltered(tbankTransaction);
                        return null;
                    } else {
                        TransactionRequest clearTransaction = tbankTransactionService.toTransaction(tbankTransaction, accountId);
                        TransactionSummary result = transactionService.saveTransaction(clearTransaction);
                        tbankTransaction = tbankTransactionService.setParsed(tbankTransaction);
                        return result;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        final var parsed = newClearTransaction.stream().count();
        final var filtered = newUniqueTransaction.stream().count() - parsed;

        tbankImport = importRepository.save(tbankImport.setSuccess(rowTransactions.size(), skipped, saved, parsed, filtered));
        return mapper.map(tbankImport);
    }
}
