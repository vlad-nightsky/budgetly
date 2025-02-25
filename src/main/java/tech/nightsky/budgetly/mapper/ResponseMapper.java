package tech.nightsky.budgetly.mapper;

import org.mapstruct.Mapper;
import tech.nightsky.budgetly.dto.response.AccountResponse;
import tech.nightsky.budgetly.dto.response.CategoryResponse;
import tech.nightsky.budgetly.dto.response.TransactionResponse;
import tech.nightsky.budgetly.model.Account;
import tech.nightsky.budgetly.model.Category;
import tech.nightsky.budgetly.model.Transaction;

@Mapper(componentModel = "spring")
public interface ResponseMapper {
    AccountResponse map(Account account);

    CategoryResponse map(Category category);

    TransactionResponse map(Transaction transaction);
}
