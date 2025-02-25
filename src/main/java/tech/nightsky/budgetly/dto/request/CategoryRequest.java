package tech.nightsky.budgetly.dto.request;

/**
 * @param name      Название категории
 * @param accountId Идентификатор аккаунта пользователя
 */
public record CategoryRequest(String name, Long accountId) {
}
