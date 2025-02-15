package tech.nightsky.budgetly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.nightsky.budgetly.model.Category;

/**
 * Репозиторий для управления категориями трат
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
