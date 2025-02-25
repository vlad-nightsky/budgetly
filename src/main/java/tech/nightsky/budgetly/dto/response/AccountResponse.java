package tech.nightsky.budgetly.dto.response;

import java.time.LocalDateTime;

public record AccountResponse(
        Long id,
        String username,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {
}
