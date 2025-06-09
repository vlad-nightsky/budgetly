package tech.nightsky.budgetly.category;

import lombok.RequiredArgsConstructor;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;
import tech.nightsky.budgetly.account.AccountCreated;

@Component
@RequiredArgsConstructor
public class CategoryEventHandler {
    private final CategoryService categoryService;

    @ApplicationModuleListener
    public void handle(AccountCreated event) {
        categoryService.createDefaultCategories(event.account());
    }
}
