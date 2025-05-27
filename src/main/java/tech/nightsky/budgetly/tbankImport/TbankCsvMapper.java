package tech.nightsky.budgetly.tbankImport;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TbankCsvMapper {
    TbankImportResponse map(TbankImport tbankImport);
}
