package tech.nightsky.budgetly.account;

import org.mapstruct.Mapper;
import tech.nightsky.budgetly.account.dto.AccountResponse;
import tech.nightsky.budgetly.account.dto.AccountSummary;

@Mapper(componentModel = "spring")
public interface AccountControllerMapper {
    AccountResponse map(AccountSummary summary);
}
