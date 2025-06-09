package tech.nightsky.budgetly.tbankImport;

import org.springframework.web.multipart.MultipartFile;

public interface ImportService {
    public TbankImportSummary importCsv(MultipartFile file);
}
