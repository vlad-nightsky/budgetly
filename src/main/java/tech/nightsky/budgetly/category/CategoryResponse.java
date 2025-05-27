package tech.nightsky.budgetly.category;

import io.swagger.v3.oas.annotations.media.Schema;
import tech.nightsky.budgetly.account.AccountResponse;
import tech.nightsky.budgetly.core.ToRefactoringDocs;

import java.time.LocalDateTime;

@Schema(
        title = ToRefactoringDocs.CategoryResponse.TITLE,
        description = ToRefactoringDocs.CategoryResponse.DESCRIPTION
)
public record CategoryResponse(
        @Schema(
                title = ToRefactoringDocs.CategoryResponse.Id.TITLE,
                description = ToRefactoringDocs.CategoryResponse.Id.DESCRIPTION,
                accessMode = Schema.AccessMode.READ_ONLY,
                example = ToRefactoringDocs.CategoryResponse.Id.EXAMPLE
        )
        Long id,

        @Schema(
                title = ToRefactoringDocs.CategoryResponse.Name.TITLE,
                description = ToRefactoringDocs.CategoryResponse.Name.DESCRIPTION,
                accessMode = Schema.AccessMode.READ_WRITE,
                example = ToRefactoringDocs.CategoryResponse.Name.EXAMPLE
        )
        String name,

        @Schema(
                title = ToRefactoringDocs.CategoryResponse.Account.TITLE,
                description = ToRefactoringDocs.CategoryResponse.Account.DESCRIPTION,
                accessMode = Schema.AccessMode.READ_ONLY
        )
        AccountResponse account,

        @Schema(
                title = ToRefactoringDocs.CategoryResponse.CreatedAt.TITLE,
                description = ToRefactoringDocs.CategoryResponse.CreatedAt.DESCRIPTION,
                accessMode = Schema.AccessMode.READ_ONLY,
                example = ToRefactoringDocs.CategoryResponse.CreatedAt.EXAMPLE
        )
        LocalDateTime createdAt,

        @Schema(
                title = ToRefactoringDocs.CategoryResponse.UpdatedAt.TITLE,
                description = ToRefactoringDocs.CategoryResponse.UpdatedAt.DESCRIPTION,
                accessMode = Schema.AccessMode.READ_ONLY,
                example = ToRefactoringDocs.CategoryResponse.UpdatedAt.EXAMPLE
        )
        LocalDateTime updatedAt
) {
}
