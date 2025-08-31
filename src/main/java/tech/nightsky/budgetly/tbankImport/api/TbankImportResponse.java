package tech.nightsky.budgetly.tbankImport.api;

import tech.nightsky.budgetly.tbankImport.ImportStatus;

public record TbankImportResponse(
        Long id,
        Integer transactionCount,
        Integer skipped,
        Integer saved,
        Integer parsed,
        Integer filtered,
        ImportStatus status) {
}
