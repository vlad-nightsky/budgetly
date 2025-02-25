package tech.nightsky.budgetly.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;
import tech.nightsky.budgetly.dto.request.CategoryRequest;
import tech.nightsky.budgetly.model.Category;
import tech.nightsky.budgetly.repository.CategoryRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Сервис обработки категории трат.
 * <p>
 * Бизнес логика
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository repository;
    private final AccountService accountService;

    public List<Category> getAllCategories() {
        return repository.findAll();
    }

    public Optional<Category> getCategoryById(Long id) {
        return repository.findById(id);
    }

    public Category saveCategory(CategoryRequest request) {
        val account = accountService.getAccountById(request.accountId())
                //todo корректная система ошибок
                .orElseThrow(() -> new IllegalArgumentException("Account no found"));

        val newCategory = Category.builder()
                .account(account)
                .name(request.name())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        val savedCategory = repository.save(newCategory);

        log.info("Категория с идентификатором: {} сохранён успешно", newCategory.getId());
        return savedCategory;
    }

    public Category updateCategory(Long id, CategoryRequest request) {
        val existingCategory = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category no found"));

        existingCategory.setUpdatedAt(LocalDateTime.now());
        existingCategory.setName(request.name());

        val account = accountService.getAccountById(request.accountId())
                //todo корректная система ошибок
                .orElseThrow(() -> new IllegalArgumentException("Account no found"));
        existingCategory.setAccount(account);

        val updatedCategory = repository.save(existingCategory);

        log.info("Категория с идентификатором: {} обновлён успешно", updatedCategory.getId());
        return updatedCategory;
    }

    public void deleteCategoryById(Long id) {
        repository.deleteById(id);
    }
}
