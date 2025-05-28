package tech.nightsky.budgetly.transaction;

import org.springframework.modulith.NamedInterface;
import org.springframework.stereotype.Service;
import tech.nightsky.budgetly.transaction.dto.TransactionRequest;
import tech.nightsky.budgetly.transaction.dto.TransactionSummary;

import java.util.List;
import java.util.Optional;

/**
 * Сервис обработки транзакций.
 * <p>
 * Бизнес логика
 */
@Service
@NamedInterface("transaction")
public interface TransactionService {

    List<TransactionSummary> getAllTransactions();

    Optional<TransactionSummary> getTransactionById(Long id);

    TransactionSummary saveTransaction(TransactionRequest transactionDto);

    TransactionSummary updateTransaction(Long id, TransactionRequest request);

    void deleteTransactionById(Long id);
}
