package tech.nightsky.budgetly.tbankImport;

public record TbankImportSummary(
        Long id,
        Integer transactionCount,
        Long skipped,
        Long saved,
        Long parsed,
        Long filtered,
        ImportStatus status
) {
}
