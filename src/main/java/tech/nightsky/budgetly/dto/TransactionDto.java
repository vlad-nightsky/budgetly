package tech.nightsky.budgetly.dto;

import tech.nightsky.budgetly.model.TransactionType;

import java.math.BigDecimal;

public record TransactionDto(
        BigDecimal amount,
        String description,
        TransactionType type,
        Long categoryId,
        Long accountId) {
}
