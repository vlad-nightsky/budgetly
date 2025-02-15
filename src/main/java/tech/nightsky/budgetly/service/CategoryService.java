package tech.nightsky.budgetly.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tech.nightsky.budgetly.dto.CategoryDto;
import tech.nightsky.budgetly.model.Account;
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

    public Category getCategoryById(Long id) {
        Optional<Category> optionalCategory = repository.findById(id);
        if (optionalCategory.isPresent()) {
            return optionalCategory.get();
        }
        log.info("Категория с идентификатором: {} не существует", id);
        return null;
    }

    public Category saveCategory(CategoryDto categoryDto) {
        Account account = accountService.getAccountById(categoryDto.accountId())
                //todo корректная система ошибок
                .orElseThrow(() -> new IllegalArgumentException("Account no found"));

        Category newCategory = Category.builder()
                .account(account)
                .name(categoryDto.name())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Category savedCategory = repository.save(newCategory);

        log.info("Категория с идентификатором: {} сохранён успешно", newCategory.getId());
        return savedCategory;
    }

    public Category updateCategory(Long categoryId, CategoryDto categoryDto) {
        Category existingCategory = repository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Category no found"));

        existingCategory.setUpdatedAt(LocalDateTime.now());
        existingCategory.setName(categoryDto.name());

        Account account = accountService.getAccountById(categoryDto.accountId())
                //todo корректная система ошибок
                .orElseThrow(() -> new IllegalArgumentException("Account no found"));
        existingCategory.setAccount(account);

        Category updatedCategory = repository.save(existingCategory);

        log.info("Категория с идентификатором: {} обновлён успешно", updatedCategory.getId());
        return updatedCategory;
    }

    public void deleteCategoryById(Long id) {
        repository.deleteById(id);
    }
}
