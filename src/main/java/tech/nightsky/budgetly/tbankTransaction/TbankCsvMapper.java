package tech.nightsky.budgetly.tbankTransaction;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TbankCsvMapper {
    TbankImportResponse map(TbankImport tbankImport);
}
