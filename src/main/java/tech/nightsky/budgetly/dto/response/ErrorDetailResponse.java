package tech.nightsky.budgetly.dto.response;

/**
 * Класс ErrorDetailResponse представляет детализированную информацию об ошибке.
 * Используется для указания конкретных полей и сообщений об ошибках.
 *
 * @param field   Название поля, связанного с ошибкой (например, "email", "password").
 * @param message Сообщение об ошибке для конкретного поля.
 */
public record ErrorDetailResponse(String field, String message) {
}
