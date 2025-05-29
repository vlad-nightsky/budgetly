package tech.nightsky.budgetly.tbankTransaction;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public interface TbankTransactionService {
    List<TbankTransactionSummary> parseCsv(MultipartFile file, Long tbankImportId);

    Boolean trySave(TbankTransactionSummary transaction);
}
