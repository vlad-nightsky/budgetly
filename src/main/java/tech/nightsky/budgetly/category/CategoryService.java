package tech.nightsky.budgetly.category;

import tech.nightsky.budgetly.account.AccountSummary;
import tech.nightsky.budgetly.category.api.CategoryRequest;

import java.util.List;
import java.util.Optional;

/**
 * Сервис обработки категории трат.
 * <p>
 * Бизнес логика
 */
public interface CategoryService {

    List<CategorySummary> getAllCategories();

    Optional<CategorySummary> getCategoryById(Long id);

    CategorySummary saveCategory(CategoryRequest request);

    CategorySummary updateCategory(Long id, CategoryRequest request);

    void deleteCategoryById(Long id);

    /**
     * Для указанного гостя создаёт список стандартных категорий.
     * Игнорирует категорию, если категория со стандартным кодом уже создана.
     *
     * @param account аккаунт пользователя
     * @return список только тех стандартных категорий, которые созданы.
     */
    List<CategorySummary> createDefaultCategories(AccountSummary account);
}
