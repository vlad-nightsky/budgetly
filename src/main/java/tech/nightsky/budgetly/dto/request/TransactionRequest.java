package tech.nightsky.budgetly.dto.request;

import tech.nightsky.budgetly.model.TransactionType;

import java.math.BigDecimal;

public record TransactionRequest(
        BigDecimal amount,
        String description,
        TransactionType type,
        Long categoryId,
        Long accountId) {
}
