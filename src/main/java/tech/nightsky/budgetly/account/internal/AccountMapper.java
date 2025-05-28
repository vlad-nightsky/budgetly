package tech.nightsky.budgetly.account.internal;

import org.mapstruct.Mapper;
import tech.nightsky.budgetly.account.dto.AccountSummary;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    AccountSummary map(Account account);
}
