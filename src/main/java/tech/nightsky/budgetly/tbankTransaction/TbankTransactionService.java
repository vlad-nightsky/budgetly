package tech.nightsky.budgetly.tbankTransaction;

import org.springframework.lang.NonNull;
import org.springframework.web.multipart.MultipartFile;
import tech.nightsky.budgetly.category.CategorySummary;
import tech.nightsky.budgetly.transaction.TransactionRequest;

import java.util.List;

public interface TbankTransactionService {
    List<TbankTransactionSummary> parseCsv(MultipartFile file, Long tbankImportId);

    Boolean trySave(TbankTransactionSummary transaction);

    /**
     * Гарантированна есть категория Unknown, поэтому результат гарантированно не NULL.
     *
     * @param transaction
     * @return
     */
    @NonNull
    CategorySummary findCategoryByTbankTransaction(TbankTransactionSummary transaction, Long accountId);

    TransactionRequest toTransaction(TbankTransactionSummary tbankTransaction, Long accountId);

    boolean isFiltered(TbankTransactionSummary tbankTransaction);

    TbankTransactionSummary setFiltered(TbankTransactionSummary tbankTransaction);

    TbankTransactionSummary setParsed(TbankTransactionSummary tbankTransaction);
}