package tech.nightsky.budgetly.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import tech.nightsky.budgetly.doc.Docs;

/**
 * Запрос на содание или обновление категории трат пользователя
 *
 * @param name      Название категории трат
 * @param accountId Идентификатор аккаунта пользователя
 */
@Schema(title = Docs.CategoryRequest.TITLE,
        description = Docs.CategoryRequest.DESCRIPTION
)
public record CategoryRequest(
        @Schema(
                title = Docs.CategoryRequest.Name.TITLE,
                description = Docs.CategoryRequest.Name.DESCRIPTION,
                accessMode = Schema.AccessMode.READ_WRITE,
                example = Docs.CategoryRequest.Name.EXAMPLE,
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        String name,

        @Schema(
                title = Docs.CategoryRequest.AccountId.TITLE,
                description = Docs.CategoryRequest.AccountId.DESCRIPTION,
                accessMode = Schema.AccessMode.READ_WRITE,
                example = Docs.CategoryRequest.AccountId.EXAMPLE,
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        Long accountId
) {
}