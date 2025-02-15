package tech.nightsky.budgetly.dto;

/**
 * @param name      Название категории
 * @param accountId Идентификатор аккаунта пользователя
 */
public record CategoryDto(String name, Long accountId) {
}
