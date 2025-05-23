package tech.nightsky.budgetly.mapper;

import org.mapstruct.Mapper;
import tech.nightsky.budgetly.dto.response.TbankImportResponse;
import tech.nightsky.budgetly.model.TbankImport;

@Mapper(componentModel = "spring")
public interface TbankCsvMapper {
    TbankImportResponse map(TbankImport tbankImport);
}
