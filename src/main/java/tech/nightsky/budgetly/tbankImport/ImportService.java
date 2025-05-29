package tech.nightsky.budgetly.tbankImport;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface ImportService {
    public TbankImportSummary importCsv(MultipartFile file);
}
