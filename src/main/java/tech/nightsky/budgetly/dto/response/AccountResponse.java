package tech.nightsky.budgetly.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;


@Schema(title = "Аккаунт", description = "Содержит информацию об аккаунте пользователя.")
public record AccountResponse(
        @Schema(
                title = "Идентификатор",
                description = "Указатель на аккаунт пользователя. Генерируется на стороне сервера.",
                accessMode = Schema.AccessMode.READ_ONLY,
                example = "1"
        )
        Long id,

        @Schema(
                title = "Имя пользователя",
                description = "Имя пользователя. Используется для авторизации.",
                accessMode = Schema.AccessMode.READ_WRITE,
                example = "catra"
        )
        String username,

        @Schema(
                title = "Дата создания",
                description = "Дата и время создания объекта.",
                accessMode = Schema.AccessMode.READ_ONLY,
                example = "2025-03-01T08:40:34.081287"
        )
        LocalDateTime createdAt,


        @Schema(
                title = "Идентификатор",
                description = "Дата и время обновления объекта.",
                accessMode = Schema.AccessMode.READ_ONLY,
                example = "2025-03-01T08:40:34.081287"
        )
        LocalDateTime updatedAt) {
}
