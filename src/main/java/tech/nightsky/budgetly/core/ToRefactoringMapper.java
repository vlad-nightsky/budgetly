package tech.nightsky.budgetly.core;

import org.mapstruct.Mapper;
import tech.nightsky.budgetly.category.CategoryResponse;
import tech.nightsky.budgetly.category.internal.Category;
import tech.nightsky.budgetly.transaction.TransactionResponse;
import tech.nightsky.budgetly.transaction.internal.Transaction;

@Mapper(componentModel = "spring")
public interface ToRefactoringMapper {
    CategoryResponse map(Category category);

    TransactionResponse map(Transaction transaction);
}
