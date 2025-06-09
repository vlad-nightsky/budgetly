package tech.nightsky.budgetly.category.internal;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для управления категориями трат
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
    /**
     * Проверяет существование категории с указанным кодом для конкретного аккаунта
     *
     * @param code      код категории
     * @param accountId идентификатор аккаунта
     * @return true если категория существует, false в противном случае
     */
    boolean existsByCodeAndAccountId(String code, Long accountId);
}
