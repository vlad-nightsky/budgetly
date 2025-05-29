package tech.nightsky.budgetly.transaction;

import java.math.BigDecimal;

public record TransactionSummary(
        Long id,
        BigDecimal amount,
        String description,
        TransactionType type,
        Long categoryId,
        Long accountId) {
}
