package tech.nightsky.budgetly.category;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Сервис обработки категории трат.
 * <p>
 * Бизнес логика
 */
@Service
public interface CategoryService {

    List<CategorySummary> getAllCategories();

    Optional<CategorySummary> getCategoryById(Long id);

    CategorySummary saveCategory(CategoryRequest request);

    CategorySummary updateCategory(Long id, CategoryRequest request);

    void deleteCategoryById(Long id);
}
