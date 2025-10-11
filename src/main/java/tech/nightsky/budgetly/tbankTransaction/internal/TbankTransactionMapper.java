package tech.nightsky.budgetly.tbankTransaction.internal;

import org.mapstruct.Mapper;
import tech.nightsky.budgetly.tbankTransaction.TbankTransactionSummary;

@Mapper(componentModel = "spring")
public interface TbankTransactionMapper {
    TbankTransactionSummary toSummary(TbankTransaction entity);

    TbankTransaction toEntity(TbankTransactionSummary summary);
}
