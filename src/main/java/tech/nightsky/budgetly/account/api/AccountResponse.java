package tech.nightsky.budgetly.account.api;

import io.swagger.v3.oas.annotations.media.Schema;
import tech.nightsky.budgetly.core.ToRefactoringDocs;

@Schema(
        title = ToRefactoringDocs.AccountResponse.TITLE,
        description = ToRefactoringDocs.AccountResponse.DESCRIPTION
)
public record AccountResponse(
        @Schema(
                title = ToRefactoringDocs.AccountResponse.Id.TITLE,
                description = ToRefactoringDocs.AccountResponse.Id.DESCRIPTION,
                accessMode = Schema.AccessMode.READ_ONLY,
                example = ToRefactoringDocs.AccountResponse.Id.EXAMPLE
        )
        Long id,

        @Schema(
                title = ToRefactoringDocs.AccountResponse.Username.TITLE,
                description = ToRefactoringDocs.AccountResponse.Username.DESCRIPTION,
                accessMode = Schema.AccessMode.READ_WRITE,
                example = ToRefactoringDocs.AccountResponse.Username.EXAMPLE
        )
        String username
) {
}