package tech.nightsky.budgetly.account;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для управления пользователём
 */
public interface AccountRepository extends JpaRepository<Account, Long> {
}
