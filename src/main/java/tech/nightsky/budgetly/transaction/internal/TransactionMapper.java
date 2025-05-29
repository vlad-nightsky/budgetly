package tech.nightsky.budgetly.transaction.internal;

import org.mapstruct.Mapper;
import tech.nightsky.budgetly.transaction.TransactionResponse;
import tech.nightsky.budgetly.transaction.TransactionSummary;

@Mapper(componentModel = "spring")
interface TransactionMapper {
    TransactionSummary map(Transaction transaction);

    TransactionResponse map(TransactionSummary transaction);
}
