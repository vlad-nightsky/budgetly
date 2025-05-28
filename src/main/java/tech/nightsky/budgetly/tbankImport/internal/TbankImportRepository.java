package tech.nightsky.budgetly.tbankImport.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.nightsky.budgetly.tbankImport.TbankImport;

/**
 * Репозиторий для управления информацией об импорте транзакций из т-банка.
 */
public interface TbankImportRepository extends JpaRepository<TbankImport, Long> {
}
