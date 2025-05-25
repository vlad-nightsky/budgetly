package tech.nightsky.budgetly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.nightsky.budgetly.model.TbankImport;

/**
 * Репозиторий для управления информацией об импорте транзакций из т-банка.
 */
public interface TbankImportRepository extends JpaRepository<TbankImport, Long> {
}
