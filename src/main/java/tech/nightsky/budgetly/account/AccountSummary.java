package tech.nightsky.budgetly.account;

/**
 * Информация об аккаунте пользователя для остальных модулей
 *
 * @param id       идентификатор покупателя
 * @param username имя пользвоателя
 */
public record AccountSummary(
        Long id,
        String username
) {
}
