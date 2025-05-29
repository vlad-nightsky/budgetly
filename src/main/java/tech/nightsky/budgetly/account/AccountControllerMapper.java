package tech.nightsky.budgetly.account;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountControllerMapper {
    AccountResponse map(AccountSummary summary);
}
