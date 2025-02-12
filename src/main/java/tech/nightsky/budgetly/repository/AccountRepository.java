package tech.nightsky.budgetly.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.nightsky.budgetly.model.Account;

/**
 * Репозиторий для управления пользователём
 */
public interface AccountRepository extends JpaRepository<Account, Long> {
}
