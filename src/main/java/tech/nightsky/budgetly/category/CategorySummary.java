package tech.nightsky.budgetly.category;

public record CategorySummary(
        Long id,
        String name,
        String code,
        Long accountId
) {
}
