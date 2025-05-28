package tech.nightsky.budgetly.tbankTransaction.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.nightsky.budgetly.transaction.internal.Transaction;

/**
 * Репозиторий для управления транзакциями
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
