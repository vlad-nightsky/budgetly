package tech.nightsky.budgetly.tbankImport.internal;

import org.mapstruct.Mapper;
import tech.nightsky.budgetly.tbankImport.TbankImportSummary;

@Mapper(componentModel = "spring")
interface TbankImportMapper {
    TbankImportSummary map(TbankImport entity);
}
