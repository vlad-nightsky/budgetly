package tech.nightsky.budgetly.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

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
                title = "Тип ошибки",
                description = "Общий тип ошибки (например, \"ValidationError\", \"InternalServerError\", \"NotFound\").",
                accessMode = Schema.AccessMode.READ_ONLY,
                example = "ValidationError"
        )
        String error,

        @Schema(
                title = "Сообщение об ошибке",
                description = "Сообщение об ошибке, предназначенное для пользователя или разработчика.",
                accessMode = Schema.AccessMode.READ_ONLY,
                example = "Invalid input data"
        )
        String message,

        @Schema(
                title = "Сообщение об ошибке",
                description = "HTTP-статус ошибки",
                accessMode = Schema.AccessMode.READ_ONLY,
                example = "400"
        )
        Integer code,

        @Schema(
                title = "Детали ошибки",
                description = "Список содержащий детализированную информацию об ошибке",
                accessMode = Schema.AccessMode.READ_ONLY
        )
        List<ErrorDetailResponse> details) {
};
