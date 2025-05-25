package tech.nightsky.budgetly.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import tech.nightsky.budgetly.model.TbankImport;
import tech.nightsky.budgetly.model.TbankTransaction;
import tech.nightsky.budgetly.repository.TbankImportRepository;
import tech.nightsky.budgetly.repository.TbankTransactionRepository;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ImportService {
    private final TbankImportRepository importRepository;
    private final TbankTransactionRepository transactionRepository;

    public TbankImport importCsv(MultipartFile file) {
        //при сохранении объекту присвоен ID
        TbankImport tbankImport = importRepository.save(
                TbankImport.init()
        );
        try {
            var finalTbankImport = tbankImport;
            List<TbankTransaction> transactions = CsvParser.csvToTransaction(file.getInputStream())
                    .stream()
                    .peek(tr -> {
                        tr.setTbankImport(finalTbankImport);
                        tr.setRowHash(tr.hashCode());
                        tr.setUpdatedAt(LocalDateTime.now());
                    })
                    .toList();
            transactions = transactionRepository.saveAll(transactions);
            tbankImport = importRepository.save(
                    tbankImport.setSuccess(transactions.size())
            );
            return tbankImport;
        } catch (IOException ex) {
            importRepository.save(
                    tbankImport.setError()
            );
            throw new RuntimeException("Data is not store successfully: " + ex.getMessage());
        }
    }
}
