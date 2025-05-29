package tech.nightsky.budgetly.transaction;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
interface TransactionControllerMapper {
    TransactionResponse map(TransactionSummary transaction);
}
