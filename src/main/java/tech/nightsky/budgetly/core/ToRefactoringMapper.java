package tech.nightsky.budgetly.core;

import org.mapstruct.Mapper;
import tech.nightsky.budgetly.account.AccountResponse;
import tech.nightsky.budgetly.category.CategoryResponse;
import tech.nightsky.budgetly.transaction.TransactionResponse;
import tech.nightsky.budgetly.account.Account;
import tech.nightsky.budgetly.category.Category;
import tech.nightsky.budgetly.transaction.Transaction;

@Mapper(componentModel = "spring")
public interface ToRefactoringMapper {
    AccountResponse map(Account account);

    CategoryResponse map(Category category);

    TransactionResponse map(Transaction transaction);
}
