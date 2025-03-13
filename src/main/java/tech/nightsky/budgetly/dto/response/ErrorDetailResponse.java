package tech.nightsky.budgetly.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Класс ErrorDetailResponse представляет детализированную информацию об ошибке.
 * Используется для указания конкретных полей и сообщений об ошибках.
 *
 * @param field   Название поля, связанного с ошибкой (например, "email", "password").
 * @param message Сообщение об ошибке для конкретного поля.
 */
@Schema(title = "Детализированная информация об ошибке", description = "Представляет детализированную информацию об ошибке. Используется для указания конкретных полей и сообщений об ошибках.")
public record ErrorDetailResponse(
        @Schema(
                title = "Название поля",
                description = "Название поля, связанного с ошибкой (например, \"email\", \"password\").",
                accessMode = Schema.AccessMode.READ_ONLY,
                example = "email"
        )
        String field,

        @Schema(
                title = "Название поля",
                description = "Сообщение об ошибке для конкретного поля. Сообщение поможет понять как правильно лидировать данные.",
                accessMode = Schema.AccessMode.READ_ONLY,
                example = "Email must be a valid format"
        )
        String message) {
}
