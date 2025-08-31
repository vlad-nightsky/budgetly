package tech.nightsky.budgetly.tbankImport;

public record TbankImportSummary(
        Long id,
        Integer transactionCount,
        Integer skipped,
        Integer saved,
        Integer parsed,
        Integer filtered,
        ImportStatus status
) {
}
