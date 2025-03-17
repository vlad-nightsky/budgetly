package tech.nightsky.budgetly.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import tech.nightsky.budgetly.doc.Docs;

/**
 * Класс ErrorDetailResponse представляет детализированную информацию об ошибке.
 * Используется для указания конкретных полей и сообщений об ошибках.
 *
 * @param field   Название поля, связанного с ошибкой (например, "email", "password").
 * @param message Сообщение об ошибке для конкретного поля.
 */
@Schema(
        title = Docs.ErrorDetailResponse.TITLE,
        description = Docs.ErrorDetailResponse.DESCRIPTION
)
public record ErrorDetailResponse(
        @Schema(
                title = Docs.ErrorDetailResponse.Field.TITLE,
                description = Docs.ErrorDetailResponse.Field.DESCRIPTION,
                accessMode = Schema.AccessMode.READ_ONLY,
                example = Docs.ErrorDetailResponse.Field.EXAMPLE
        )
        String field,

        @Schema(
                title = Docs.ErrorDetailResponse.Message.TITLE,
                description = Docs.ErrorDetailResponse.Message.DESCRIPTION,
                accessMode = Schema.AccessMode.READ_ONLY,
                example = Docs.ErrorDetailResponse.Message.EXAMPLE
        )
        String message
) {
}
