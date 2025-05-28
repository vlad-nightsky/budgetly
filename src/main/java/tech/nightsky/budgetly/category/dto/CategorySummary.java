package tech.nightsky.budgetly.category.dto;

import org.springframework.modulith.NamedInterface;

@NamedInterface("category")
public record CategorySummary(
        Long id,
        String name,
        Long accountId
) {
}
