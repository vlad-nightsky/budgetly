package tech.nightsky.budgetly.transaction;

import org.mapstruct.Mapper;
import tech.nightsky.budgetly.transaction.dto.TransactionResponse;
import tech.nightsky.budgetly.transaction.dto.TransactionSummary;

@Mapper(componentModel = "spring")
interface TransactionControllerMapper {
    TransactionResponse map(TransactionSummary transaction);
}
