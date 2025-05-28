package tech.nightsky.budgetly.account.internal;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для управления пользователём
 */
public interface AccountRepository extends JpaRepository<Account, Long> {
}
