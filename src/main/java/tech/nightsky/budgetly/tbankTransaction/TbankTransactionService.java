package tech.nightsky.budgetly.tbankTransaction;

import org.springframework.web.multipart.MultipartFile;
import tech.nightsky.budgetly.category.CategorySummary;
import tech.nightsky.budgetly.transaction.TransactionRequest;

import java.util.List;

public interface TbankTransactionService {
    List<TbankTransactionSummary> parseCsv(MultipartFile file, Long tbankImportId);

    Boolean trySave(TbankTransactionSummary transaction);

    CategorySummary findCategoryByTbankTransaction(TbankTransactionSummary transaction, Long accountId);

    TransactionRequest toTransaction(TbankTransactionSummary tbankTransaction, Long accountId);

    boolean isFiltered(TbankTransactionSummary tbankTransaction);

    void setFiltered(TbankTransactionSummary tbankTransaction);

    void setParsed(TbankTransactionSummary tbankTransaction);
}