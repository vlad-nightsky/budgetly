package tech.nightsky.budgetly.tbankTransaction;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import tech.nightsky.budgetly.tbankTransaction.internal.TbankImportRepository;
import tech.nightsky.budgetly.tbankTransaction.internal.TbankTransaction;
import tech.nightsky.budgetly.tbankTransaction.internal.TbankTransactionRepository;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ImportService {
    private final TbankImportRepository importRepository;
    private final TbankTransactionRepository transactionRepository;

    public TbankImport importCsv(MultipartFile file) {
        //при сохранении объекту присвоен ID
        TbankImport tbankImport = importRepository.save(TbankImport.init());
        try {
            var finalTbankImport = tbankImport;
            List<TbankTransaction> rowTransactions = CsvParser.csvToTransaction(file.getInputStream())
                    .stream()
                    .peek(tr -> {
                        tr.setTbankImport(finalTbankImport);
                        tr.setRowHash(tr.hashCode());
                        tr.setUpdatedAt(LocalDateTime.now());
                    }).toList();

            var skipped = 0;
            var saved = 0;
            var newUniqueTransaction = new ArrayList<TbankTransaction>();
            for (TbankTransaction transaction : rowTransactions) {
                var rowHash = transaction.getRowHash();
                if (transactionRepository.existsByRowHash(rowHash)) {
                    var exisingTransactions = transactionRepository.findByRowHash(rowHash);
                    if (exisingTransactions.stream().anyMatch(t -> t.equals(transaction))) {
                        skipped++;
                        continue;
                    }
                }
                newUniqueTransaction.add(transactionRepository.save(transaction));
                saved++;
            }

            tbankImport = importRepository.save(tbankImport.setSuccess(rowTransactions.size(), skipped, saved));
            return tbankImport;
        } catch (IOException ex) {
            importRepository.save(tbankImport.setError());
            throw new RuntimeException("Data is not store successfully: " + ex.getMessage());
        }
    }
}
