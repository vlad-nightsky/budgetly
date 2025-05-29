package tech.nightsky.budgetly.transaction.internal;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для управления транзакциями
 */
interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
