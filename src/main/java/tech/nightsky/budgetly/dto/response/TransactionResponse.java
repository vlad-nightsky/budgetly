package tech.nightsky.budgetly.dto.response;

import tech.nightsky.budgetly.model.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionResponse(
        Long id,
        BigDecimal amount,
        String description,
        TransactionType type,
        CategoryResponse category,
        AccountResponse account,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
}
