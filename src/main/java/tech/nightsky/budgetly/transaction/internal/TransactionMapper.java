package tech.nightsky.budgetly.transaction.internal;

import org.mapstruct.Mapper;
import tech.nightsky.budgetly.transaction.TransactionSummary;
import tech.nightsky.budgetly.transaction.api.TransactionResponse;

@Mapper(componentModel = "spring")
interface TransactionMapper {
    TransactionSummary map(Transaction transaction);

    TransactionResponse map(TransactionSummary transaction);
}
