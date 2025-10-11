package tech.nightsky.budgetly.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionSummary(
        Long id,
        BigDecimal amount,
        String description,
        TransactionType type,
        Long categoryId,
        Long accountId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
