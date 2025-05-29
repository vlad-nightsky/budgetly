package tech.nightsky.budgetly.account.internal;

import org.mapstruct.Mapper;
import tech.nightsky.budgetly.account.AccountSummary;

@Mapper(componentModel = "spring")
interface AccountMapper {
    AccountSummary map(Account account);
}
