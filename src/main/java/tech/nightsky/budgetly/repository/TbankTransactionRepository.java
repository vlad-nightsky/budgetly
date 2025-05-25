package tech.nightsky.budgetly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.nightsky.budgetly.model.TbankTransaction;

import java.util.List;

/**
 * Репозиторий для управления сырыvb данные транзакциями из Т-банк с привязкой к сессии импорта.
 */
public interface TbankTransactionRepository extends JpaRepository<TbankTransaction, Long> {
    List<TbankTransaction> findByRowHash(Integer rowHash);

    boolean existsByRowHash(Integer rowHash);
}
