package tech.nightsky.budgetly.tbankImport;

public record TbankImportSummary(
        Long id,
        Integer transactionCount,
        Integer saved,
        Integer skipped,
        ImportStatus status
) {
}
