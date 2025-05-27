package tech.nightsky.budgetly.tbankTransaction;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.nightsky.budgetly.transaction.Transaction;

/**
 * Репозиторий для управления транзакциями
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
