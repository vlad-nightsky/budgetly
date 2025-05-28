package tech.nightsky.budgetly.transaction;

import io.swagger.v3.oas.annotations.media.Schema;
import tech.nightsky.budgetly.account.AccountResponse;
import tech.nightsky.budgetly.category.CategoryResponse;
import tech.nightsky.budgetly.core.ToRefactoringDocs;
import tech.nightsky.budgetly.tbankTransaction.internal.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(title = ToRefactoringDocs.TransactionResponse.TITLE, description = ToRefactoringDocs.TransactionResponse.DESCRIPTION)
public record TransactionResponse(
        @Schema(
                title = ToRefactoringDocs.TransactionResponse.Id.TITLE,
                description = ToRefactoringDocs.TransactionResponse.Id.DESCRIPTION,
                accessMode = Schema.AccessMode.READ_ONLY,
                example = ToRefactoringDocs.TransactionResponse.Id.EXAMPLE
        )
        Long id,

        @Schema(
                title = ToRefactoringDocs.TransactionResponse.Amount.TITLE,
                description = ToRefactoringDocs.TransactionResponse.Amount.DESCRIPTION,
                accessMode = Schema.AccessMode.READ_WRITE,
                example = ToRefactoringDocs.TransactionResponse.Amount.EXAMPLE,
                format = ToRefactoringDocs.TransactionResponse.Amount.FORMAT,
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        BigDecimal amount,

        @Schema(
                title = ToRefactoringDocs.TransactionResponse.Description.TITLE,
                description = ToRefactoringDocs.TransactionResponse.Description.DESCRIPTION,
                accessMode = Schema.AccessMode.READ_WRITE,
                example = ToRefactoringDocs.TransactionResponse.Description.EXAMPLE
        )
        String description,

        @Schema(
                title = ToRefactoringDocs.TransactionResponse.Type.TITLE,
                description = ToRefactoringDocs.TransactionResponse.Type.DESCRIPTION,
                accessMode = Schema.AccessMode.READ_WRITE,
                example = ToRefactoringDocs.TransactionResponse.Type.EXAMPLE,
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        TransactionType type,

        @Schema(
                title = ToRefactoringDocs.TransactionResponse.Category.TITLE,
                description = ToRefactoringDocs.TransactionResponse.Category.DESCRIPTION,
                accessMode = Schema.AccessMode.READ_ONLY
        )
        CategoryResponse category,

        @Schema(
                title = ToRefactoringDocs.TransactionResponse.Account.TITLE,
                description = ToRefactoringDocs.TransactionResponse.Account.DESCRIPTION,
                accessMode = Schema.AccessMode.READ_ONLY
        )
        AccountResponse account,

        @Schema(
                title = ToRefactoringDocs.TransactionResponse.CreatedAt.TITLE,
                description = ToRefactoringDocs.TransactionResponse.CreatedAt.DESCRIPTION,
                accessMode = Schema.AccessMode.READ_ONLY,
                example = ToRefactoringDocs.TransactionResponse.CreatedAt.EXAMPLE
        )
        LocalDateTime createdAt,

        @Schema(
                title = ToRefactoringDocs.TransactionResponse.UpdatedAt.TITLE,
                description = ToRefactoringDocs.TransactionResponse.UpdatedAt.DESCRIPTION,
                accessMode = Schema.AccessMode.READ_ONLY,
                example = ToRefactoringDocs.TransactionResponse.UpdatedAt.EXAMPLE
        )
        LocalDateTime updatedAt) {
}
