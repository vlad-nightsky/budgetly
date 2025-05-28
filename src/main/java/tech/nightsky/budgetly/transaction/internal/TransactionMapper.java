package tech.nightsky.budgetly.transaction.internal;

import org.mapstruct.Mapper;
import tech.nightsky.budgetly.transaction.dto.TransactionResponse;
import tech.nightsky.budgetly.transaction.dto.TransactionSummary;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    TransactionSummary map(Transaction transaction);

    TransactionResponse map(TransactionSummary transaction);
}
