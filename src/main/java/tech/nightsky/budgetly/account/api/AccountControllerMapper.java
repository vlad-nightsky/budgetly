package tech.nightsky.budgetly.account.api;

import org.mapstruct.Mapper;
import tech.nightsky.budgetly.account.AccountSummary;

@Mapper(componentModel = "spring")
public interface AccountControllerMapper {
    AccountResponse map(AccountSummary summary);
}
