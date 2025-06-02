package tech.nightsky.budgetly.tbankImport.internal;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для управления информацией об импорте транзакций из т-банка.
 */
interface TbankImportRepository extends JpaRepository<TbankImport, Long> {
}
