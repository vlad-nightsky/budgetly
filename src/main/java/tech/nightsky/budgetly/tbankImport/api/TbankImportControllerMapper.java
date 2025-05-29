package tech.nightsky.budgetly.tbankImport.api;

import org.mapstruct.Mapper;
import tech.nightsky.budgetly.tbankImport.TbankImportSummary;

@Mapper(componentModel = "spring")
public interface TbankImportControllerMapper {
    TbankImportResponse map(TbankImportSummary tbankImport);
}
