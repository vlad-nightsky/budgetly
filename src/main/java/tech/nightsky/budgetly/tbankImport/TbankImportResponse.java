package tech.nightsky.budgetly.tbankImport;

import tech.nightsky.budgetly.core.ImportStatus;

import java.time.LocalDateTime;

public record TbankImportResponse(
        Long id,
        Integer transactionCount,
        Integer skipped,
        Integer saved,
        ImportStatus status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
}
