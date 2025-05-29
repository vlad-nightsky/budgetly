package tech.nightsky.budgetly.tbankImport.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import tech.nightsky.budgetly.tbankImport.ImportService;
import tech.nightsky.budgetly.tbankImport.TbankImportSummary;
import tech.nightsky.budgetly.tbankTransaction.TbankTransactionService;
import tech.nightsky.budgetly.tbankTransaction.TbankTransactionSummary;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class ImportServiceImpl implements ImportService {
    private final TbankImportRepository importRepository;
    private final TbankImportMapper mapper;
    private final TbankTransactionService transactionService;

    @Override
    public TbankImportSummary importCsv(MultipartFile file) {
        //при сохранении объекту присвоен ID
        TbankImport tbankImport = importRepository.save(TbankImport.init());
        List<TbankTransactionSummary> rowTransactions = transactionService.parseCsv(file, tbankImport.getId());
        int skipped = 0, saved = 0;
        var newUniqueTransaction = new ArrayList<TbankTransactionSummary>();

        for (TbankTransactionSummary transaction : rowTransactions) {
            Boolean isSaved = transactionService.trySave(transaction);
            if (isSaved) {
                saved++;
                newUniqueTransaction.add(transaction);
            } else {
                skipped++;
            }
        }

        tbankImport = importRepository.save(tbankImport.setSuccess(rowTransactions.size(), skipped, saved));
        return mapper.map(tbankImport);
    }
}
