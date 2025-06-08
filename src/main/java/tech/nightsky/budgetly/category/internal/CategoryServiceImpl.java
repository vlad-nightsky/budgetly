package tech.nightsky.budgetly.category.internal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import tech.nightsky.budgetly.account.AccountService;
import tech.nightsky.budgetly.account.AccountSummary;
import tech.nightsky.budgetly.category.CategoryService;
import tech.nightsky.budgetly.category.CategorySummary;
import tech.nightsky.budgetly.category.api.CategoryRequest;
import tech.nightsky.budgetly.core.Constant;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Сервис обработки категории трат.
 * <p>
 * Бизнес логика
 */
@Slf4j
@Service
@RequiredArgsConstructor
class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository repository;
    private final AccountService accountService;
    private final CategoryMapper mapper;
    private final MessageSource messageSource;

    @Override
    public List<CategorySummary> getAllCategories() {
        return repository.findAll().stream().map(mapper::map).collect(Collectors.toList());
    }

    @Override
    public Optional<CategorySummary> getCategoryById(Long id) {
        return repository.findById(id).map(mapper::map);
    }

    @Override
    public CategorySummary saveCategory(CategoryRequest request) {
        val account = accountService.getAccountById(request.accountId())
                //todo корректная система ошибок
                .orElseThrow(() -> new IllegalArgumentException("Account no found"));

        val newCategory = Category.builder()
                .accountId(account.id())
                .name(request.name())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        validateBeforeSave(newCategory);
        val savedCategory = repository.save(newCategory);

        log.info("Категория с идентификатором: {} сохранён успешно", newCategory.getId());
        return mapper.map(savedCategory);
    }

    private void validateBeforeSave(Category category) {
        if (repository.existsByCodeAndAccountId(category.getCode(), category.getAccountId()))
            throw new RuntimeException("Category code already exists");
    }

    @Override
    public CategorySummary updateCategory(Long id, CategoryRequest request) {
        val existingCategory = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category no found"));

        existingCategory.setUpdatedAt(LocalDateTime.now());
        existingCategory.setName(request.name());

        val account = accountService.getAccountById(request.accountId())
                //todo корректная система ошибок
                .orElseThrow(() -> new IllegalArgumentException("Account no found"));
        existingCategory.setAccountId(account.id());

        val updatedCategory = repository.save(existingCategory);

        log.info("Категория с идентификатором: {} обновлён успешно", updatedCategory.getId());
        return mapper.map(updatedCategory);
    }

    @Override
    public void deleteCategoryById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<CategorySummary> createDefaultCategories(AccountSummary account) {
        return Category.defaultCategoriesBuilder()
                .locale(Constant.RUSSIA)
                .messageSource(messageSource)
                .account(account)
                .build()
                .stream()
                .filter(category -> !repository.existsByCodeAndAccountId(category.getCode(), category.getAccountId()))
                .peek(repository::save)
                .map(mapper::map)
                .toList();
    }
}
