package tech.nightsky.budgetly.category.api;

import io.swagger.v3.oas.annotations.media.Schema;
import tech.nightsky.budgetly.core.ToRefactoringDocs;

/**
 * Запрос на содание или обновление категории трат пользователя
 *
 * @param name      Название категории трат
 * @param accountId Идентификатор аккаунта пользователя
 */
@Schema(title = ToRefactoringDocs.CategoryRequest.TITLE,
        description = ToRefactoringDocs.CategoryRequest.DESCRIPTION
)
public record CategoryRequest(
        @Schema(
                title = ToRefactoringDocs.CategoryRequest.Name.TITLE,
                description = ToRefactoringDocs.CategoryRequest.Name.DESCRIPTION,
                accessMode = Schema.AccessMode.READ_WRITE,
                example = ToRefactoringDocs.CategoryRequest.Name.EXAMPLE,
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        String name,

        @Schema(
                title = ToRefactoringDocs.CategoryRequest.AccountId.TITLE,
                description = ToRefactoringDocs.CategoryRequest.AccountId.DESCRIPTION,
                accessMode = Schema.AccessMode.READ_WRITE,
                example = ToRefactoringDocs.CategoryRequest.AccountId.EXAMPLE,
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        Long accountId
) {
}