package tech.nightsky.budgetly.core.api;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import tech.nightsky.budgetly.core.ToRefactoringDocs;

import java.util.List;

/**
 * Класс ErrorResponse представляет собой структуру ответа, используемую для передачи информации об ошибке.
 * Запись (record) содержит основные поля, описывающие ошибку, а также список дополнительных деталей.
 *
 * <p>Этот класс полезен для стандартизации формата ответов API при возникновении исключений или ошибок.</p>
 *
 * <p>Пример использования:</p>
 * <pre>{@code
 * ErrorResponse errorResponse = new ErrorResponse(
 *     "ValidationError",
 *     "Invalid input data",
 *     "400",
 *     List.of(new ErrorDetailResponse("email", "Email must be a valid format"))
 * );
 * }</pre>
 *
 * @param error   Общий тип ошибки (например, "ValidationError", "InternalServerError").
 * @param message Сообщение об ошибке, предназначенное для пользователя или разработчика.
 * @param code    HTTP-статус ошибки.
 * @param details Список объектов {@link ErrorDetailResponse}, содержащих детализированную информацию об ошибке.
 */
@Builder
@Schema(title = "Информация об ошибке", description = "Содержит информацию об ошибке.")
public record ErrorResponse(
        @Schema(
                title = ToRefactoringDocs.ErrorResponse.ERROR_TITLE,
                description = ToRefactoringDocs.ErrorResponse.ERROR_DESCRIPTION,
                accessMode = Schema.AccessMode.READ_ONLY,
                example = ToRefactoringDocs.ErrorResponse.ERROR_EXAMPLE
        )
        String error,

        @Schema(
                title = ToRefactoringDocs.ErrorResponse.MESSAGE_TITLE,
                description = ToRefactoringDocs.ErrorResponse.MESSAGE_DESCRIPTION,
                accessMode = Schema.AccessMode.READ_ONLY,
                example = ToRefactoringDocs.ErrorResponse.MESSAGE_EXAMPLE
        )
        String message,

        @Schema(
                title = ToRefactoringDocs.ErrorResponse.CODE_TITLE,
                description = ToRefactoringDocs.ErrorResponse.CODE_DESCRIPTION,
                accessMode = Schema.AccessMode.READ_ONLY,
                example = ToRefactoringDocs.ErrorResponse.CODE_EXAMPLE
        )
        Integer code,

        @Schema(
                title = ToRefactoringDocs.ErrorResponse.DETAILS_TITLE,
                description = ToRefactoringDocs.ErrorResponse.DETAILS_DESCRIPTION,
                accessMode = Schema.AccessMode.READ_ONLY
        )
        List<ErrorDetailResponse> details) {
}