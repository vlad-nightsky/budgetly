package tech.nightsky.budgetly.dto.response;

import tech.nightsky.budgetly.model.ImportStatus;

import java.time.LocalDateTime;

public record TbankImportResponse(
        Long id,
        Integer transactionCount,
        ImportStatus status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
}
