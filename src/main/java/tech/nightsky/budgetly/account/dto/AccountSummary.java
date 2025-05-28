package tech.nightsky.budgetly.account.dto;

import org.springframework.modulith.NamedInterface;

/**
 * Информация об аккаунте пользователя для остальных модулей
 *
 * @param id       идентификатор покупателя
 * @param username имя пользвоателя
 */
@NamedInterface("account")
public record AccountSummary(
        Long id,
        String username
) {
}
