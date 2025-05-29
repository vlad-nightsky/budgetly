package tech.nightsky.budgetly.transaction;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Сервис обработки транзакций.
 * <p>
 * Бизнес логика
 */
@Service
public interface TransactionService {

    List<TransactionSummary> getAllTransactions();

    Optional<TransactionSummary> getTransactionById(Long id);

    TransactionSummary saveTransaction(TransactionRequest transactionDto);

    TransactionSummary updateTransaction(Long id, TransactionRequest request);

    void deleteTransactionById(Long id);
}
