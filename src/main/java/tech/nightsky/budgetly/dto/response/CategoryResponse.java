package tech.nightsky.budgetly.dto.response;

import java.time.LocalDateTime;

public record CategoryResponse(
        Long id,
        String name,
        AccountResponse account,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
}
