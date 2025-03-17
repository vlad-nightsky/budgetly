package tech.nightsky.budgetly.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import tech.nightsky.budgetly.doc.Docs;

import java.util.List;

/**
 * Класс ErrorResponse представляет собой структуру ответа, используемую для передачи информации об ошибке.
 * Запись (record) содержит основные поля, описывающие ошибку, а также список дополнительных деталей.
 *
 * <p>Этот класс полезен для стандартизации формата ответов API при возникновении исключений или ошибок.</p>
 *
 * <p>Пример использования:
 * <pre>{@code
 * ErrorResponse errorResponse = new ErrorResponse(
 *     "ValidationError",
 *     "Invalid input data",
 *     "400",
 *     List.of(new ErrorDetailResponse("email", "Email must be a valid format"))
 * );
 * }</pre>
 * </p>
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
                title = Docs.ErrorResponse.ERROR_TITLE,
                description = Docs.ErrorResponse.ERROR_DESCRIPTION,
                accessMode = Schema.AccessMode.READ_ONLY,
                example = Docs.ErrorResponse.ERROR_EXAMPLE
        )
        String error,

        @Schema(
                title = Docs.ErrorResponse.MESSAGE_TITLE,
                description = Docs.ErrorResponse.MESSAGE_DESCRIPTION,
                accessMode = Schema.AccessMode.READ_ONLY,
                example = Docs.ErrorResponse.MESSAGE_EXAMPLE
        )
        String message,

        @Schema(
                title = Docs.ErrorResponse.CODE_TITLE,
                description = Docs.ErrorResponse.CODE_DESCRIPTION,
                accessMode = Schema.AccessMode.READ_ONLY,
                example = Docs.ErrorResponse.CODE_EXAMPLE
        )
        Integer code,

        @Schema(
                title = Docs.ErrorResponse.DETAILS_TITLE,
                description = Docs.ErrorResponse.DETAILS_DESCRIPTION,
                accessMode = Schema.AccessMode.READ_ONLY
        )
        List<ErrorDetailResponse> details) {
}