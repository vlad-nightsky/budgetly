package tech.nightsky.budgetly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.nightsky.budgetly.model.Transaction;

/**
 * Репозиторий для управления транзакциями
 */
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
