package tech.nightsky.budgetly.tbankImport.api;

import tech.nightsky.budgetly.tbankImport.ImportStatus;

public record TbankImportResponse(
        Long id,
        Integer transactionCount,
        Long skipped,
        Long saved,
        Long parsed,
        Long filtered,
        ImportStatus status) {
}
