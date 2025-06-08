package tech.nightsky.budgetly.tbankTransaction;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TbankTransactionService {
    List<TbankTransactionSummary> parseCsv(MultipartFile file, Long tbankImportId);

    Boolean trySave(TbankTransactionSummary transaction);
}
