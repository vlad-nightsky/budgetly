package tech.nightsky.budgetly.account.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.modulith.NamedInterface;
import tech.nightsky.budgetly.core.ToRefactoringDocs;

/**
 * Запрос на создание или обнволение аккаунта пользователя
 *
 * @param username Имя пользователя. Используется для авторизации.
 * @param password Пароль пользователя. Используется для авторизации.
 */
@Schema(
        title = ToRefactoringDocs.AccountRequest.TITLE,
        description = ToRefactoringDocs.AccountRequest.DESCRIPTION
)
@NamedInterface("account")
public record AccountRequest(
        @Schema(
                title = ToRefactoringDocs.AccountRequest.Username.TITLE,
                description = ToRefactoringDocs.AccountRequest.Username.DESCRIPTION,
                accessMode = Schema.AccessMode.READ_WRITE,
                example = ToRefactoringDocs.AccountRequest.Username.EXAMPLE,
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        String username,

        @Schema(
                title = ToRefactoringDocs.AccountRequest.Password.TITLE,
                description = ToRefactoringDocs.AccountRequest.Password.DESCRIPTION,
                accessMode = Schema.AccessMode.WRITE_ONLY,
                example = ToRefactoringDocs.AccountRequest.Password.EXAMPLE,
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        String password
) {
}