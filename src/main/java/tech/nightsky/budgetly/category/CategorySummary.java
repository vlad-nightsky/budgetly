package tech.nightsky.budgetly.category;

import java.time.LocalDateTime;

public record CategorySummary(
        Long id,
        String name,
        String code,
        Long accountId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
