package tech.nightsky.budgetly.tbankTransaction.internal;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Репозиторий для управления сырыvb данные транзакциями из Т-банк с привязкой к сессии импорта.
 */
interface TbankTransactionRepository extends JpaRepository<TbankTransaction, Long> {
    List<TbankTransaction> findByRowHash(Integer rowHash);

    boolean existsByRowHash(Integer rowHash);
}
