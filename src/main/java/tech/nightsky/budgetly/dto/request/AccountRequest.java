package tech.nightsky.budgetly.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(title = "Аккаунт", description = "Запрос на создание или обновление пользователя.")
public record AccountRequest(
        @Schema(
                title = "Имя пользователя",
                description = "Имя пользователя. Используется для авторизации.",
                accessMode = Schema.AccessMode.READ_WRITE,
                example = "catra"
        )
        String username,

        @Schema(
                title = "Пароль пользователя",
                description = "Пароль пользователя. Используется для авторизации.",
                accessMode = Schema.AccessMode.WRITE_ONLY,
                example = "adora"
        )
        String password) {
}
