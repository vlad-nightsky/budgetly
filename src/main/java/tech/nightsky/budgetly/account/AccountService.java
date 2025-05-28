package tech.nightsky.budgetly.account;

import org.springframework.stereotype.Service;
import tech.nightsky.budgetly.account.dto.AccountRequest;
import tech.nightsky.budgetly.account.dto.AccountSummary;
import tech.nightsky.budgetly.account.internal.Account;

import java.util.List;
import java.util.Optional;

/**
 * Сервис обработки пользователя.
 * <p>
 * Бизнес логика
 */
@Service
public interface AccountService {

    List<AccountSummary> getAllAccounts();

    Optional<AccountSummary> getAccountById(Long id);

    //todo добавить документацию
    AccountSummary saveAccount(AccountRequest request);

    AccountSummary updateAccount(Long id, AccountRequest request);

    void deleteAccountById(Long id);
}
