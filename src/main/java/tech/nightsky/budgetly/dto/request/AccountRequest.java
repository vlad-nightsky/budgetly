package tech.nightsky.budgetly.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import tech.nightsky.budgetly.doc.Docs;

/**
 * Запрос на создание или обнволение аккаунта пользователя
 *
 * @param username Имя пользователя. Используется для авторизации.
 * @param password Пароль пользователя. Используется для авторизации.
 */
@Schema(
        title = Docs.AccountRequest.TITLE,
        description = Docs.AccountRequest.DESCRIPTION
)
public record AccountRequest(
        @Schema(
                title = Docs.AccountRequest.Username.TITLE,
                description = Docs.AccountRequest.Username.DESCRIPTION,
                accessMode = Schema.AccessMode.READ_WRITE,
                example = Docs.AccountRequest.Username.EXAMPLE,
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        String username,

        @Schema(
                title = Docs.AccountRequest.Password.TITLE,
                description = Docs.AccountRequest.Password.DESCRIPTION,
                accessMode = Schema.AccessMode.WRITE_ONLY,
                example = Docs.AccountRequest.Password.EXAMPLE,
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        String password
) {
}