package tech.nightsky.budgetly.service;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import tech.nightsky.budgetly.model.TbankImport;
import tech.nightsky.budgetly.model.TbankTransaction;

import java.io.IOException;
import java.util.List;

@Component
public class ImportService {
    public TbankImport importCsv(MultipartFile file) {
        try {
            List < TbankTransaction > stuList = CsvParser.csvToTransaction(file.getInputStream());

            return TbankImport.builder().build();
        } catch (IOException ex) {
            throw new RuntimeException("Data is not store successfully: " + ex.getMessage());
        }
    }
}
