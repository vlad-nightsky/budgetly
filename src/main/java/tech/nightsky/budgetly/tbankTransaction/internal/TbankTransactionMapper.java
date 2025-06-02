package tech.nightsky.budgetly.tbankTransaction.internal;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tech.nightsky.budgetly.tbankTransaction.TbankTransactionSummary;

@Mapper(componentModel = "spring")
public interface TbankTransactionMapper {
    TbankTransactionSummary map(TbankTransaction entity);

    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
    TbankTransaction map(TbankTransactionSummary summary);
}
