package tech.nightsky.budgetly.tbankTransaction;

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
