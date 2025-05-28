package tech.nightsky.budgetly.transaction.dto;

import org.springframework.modulith.NamedInterface;

import java.math.BigDecimal;

@NamedInterface("transaction")
public record TransactionSummary(
        Long id,
        BigDecimal amount,
        String description,
        TransactionType type,
        Long categoryId,
        Long accountId) {
}
