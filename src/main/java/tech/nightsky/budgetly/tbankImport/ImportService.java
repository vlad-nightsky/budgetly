package tech.nightsky.budgetly.tbankImport;

import org.springframework.web.multipart.MultipartFile;

public interface ImportService {
    TbankImportSummary importCsv(MultipartFile file, Long accountId);
}
