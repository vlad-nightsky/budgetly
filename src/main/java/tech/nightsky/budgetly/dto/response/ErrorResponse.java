package tech.nightsky.budgetly.dto.response;

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
 * @param code    Код ошибки (например, HTTP-статус или внутренний код ошибки).
 * @param details Список объектов {@link ErrorDetailResponse}, содержащих детализированную информацию об ошибке.
 */
@Builder
public record ErrorResponse(String error, String message, Integer code, List<ErrorDetailResponse> details) {
};
