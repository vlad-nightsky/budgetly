package tech.nightsky.budgetly.category;

import io.swagger.v3.oas.annotations.media.Schema;
import tech.nightsky.budgetly.core.ToRefactoringDocs;

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
        //todo собразить аксесор
        Long accountId
) {
}
