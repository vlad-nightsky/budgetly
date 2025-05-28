package tech.nightsky.budgetly.account.internal.mapper;

import org.mapstruct.Mapper;
import tech.nightsky.budgetly.account.dto.AccountResponse;
import tech.nightsky.budgetly.account.dto.AccountSummary;
import tech.nightsky.budgetly.account.internal.Account;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountSummary map(Account account);

    AccountResponse map(AccountSummary summary);
}
