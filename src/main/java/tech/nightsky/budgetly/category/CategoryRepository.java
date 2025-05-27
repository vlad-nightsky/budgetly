package tech.nightsky.budgetly.category;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для управления категориями трат
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
