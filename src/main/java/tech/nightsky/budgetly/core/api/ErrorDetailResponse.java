package tech.nightsky.budgetly.core.api;

import io.swagger.v3.oas.annotations.media.Schema;
import tech.nightsky.budgetly.core.ToRefactoringDocs;

/**
 * Класс ErrorDetailResponse представляет детализированную информацию об ошибке.
 * Используется для указания конкретных полей и сообщений об ошибках.
 *
 * @param field   Название поля, связанного с ошибкой (например, "email", "password").
 * @param message Сообщение об ошибке для конкретного поля.
 */
@Schema(
        title = ToRefactoringDocs.ErrorDetailResponse.TITLE,
        description = ToRefactoringDocs.ErrorDetailResponse.DESCRIPTION
)
public record ErrorDetailResponse(
        @Schema(
                title = ToRefactoringDocs.ErrorDetailResponse.Field.TITLE,
                description = ToRefactoringDocs.ErrorDetailResponse.Field.DESCRIPTION,
                accessMode = Schema.AccessMode.READ_ONLY,
                example = ToRefactoringDocs.ErrorDetailResponse.Field.EXAMPLE
        )
        String field,

        @Schema(
                title = ToRefactoringDocs.ErrorDetailResponse.Message.TITLE,
                description = ToRefactoringDocs.ErrorDetailResponse.Message.DESCRIPTION,
                accessMode = Schema.AccessMode.READ_ONLY,
                example = ToRefactoringDocs.ErrorDetailResponse.Message.EXAMPLE
        )
        String message
) {
}
