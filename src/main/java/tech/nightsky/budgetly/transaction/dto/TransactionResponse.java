package tech.nightsky.budgetly.transaction.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.modulith.NamedInterface;
import tech.nightsky.budgetly.core.ToRefactoringDocs;

import java.math.BigDecimal;

@Schema(title = ToRefactoringDocs.TransactionResponse.TITLE, description = ToRefactoringDocs.TransactionResponse.DESCRIPTION)
@NamedInterface("transaction")
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
        Long categoryId,

        @Schema(
                title = ToRefactoringDocs.TransactionResponse.Account.TITLE,
                description = ToRefactoringDocs.TransactionResponse.Account.DESCRIPTION,
                accessMode = Schema.AccessMode.READ_ONLY
        )
        //todo собразить аксесор
        Long accountId) {
}
