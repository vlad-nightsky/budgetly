package tech.nightsky.budgetly.transaction.api;

import org.mapstruct.Mapper;
import tech.nightsky.budgetly.transaction.TransactionSummary;

@Mapper(componentModel = "spring")
interface TransactionControllerMapper {
    TransactionResponse map(TransactionSummary transaction);
}
