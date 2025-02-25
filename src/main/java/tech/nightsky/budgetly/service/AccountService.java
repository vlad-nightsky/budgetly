package tech.nightsky.budgetly.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;
import tech.nightsky.budgetly.dto.request.AccountRequest;
import tech.nightsky.budgetly.exception.NotFoundException;
import tech.nightsky.budgetly.model.Account;
import tech.nightsky.budgetly.repository.AccountRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Сервис обработки пользователя.
 * <p>
 * Бизнес логика
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository repository;

    public List<Account> getAllAccounts() {
        return repository.findAll();
    }

    public Optional<Account> getAccountById(Long id) {
        return repository.findById(id);
    }

    //todo добавить документацию
    //добавить хеширования пароля и простую авторизацию
    public Account saveAccount(AccountRequest request) {
        val account = Account.builder()
                .username(request.username())
                .password(request.password())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        val savedAccount = repository.save(account);

        log.info("Пользователь с идентификатором: {} сохранён успешно", account.getId());
        return savedAccount;
    }

    public Account updateAccount(Long id, AccountRequest request) {
        val existingAccount = repository.findById(id)
                .orElseThrow(() -> NotFoundException.of(id));

        existingAccount.setUsername(request.username());
        existingAccount.setPassword(request.password());
        existingAccount.setUpdatedAt(LocalDateTime.now());

        val updatedAccount = repository.save(existingAccount);

        log.info("Пользователь с идентификатором: {} обновлён успешно", existingAccount.getId());
        return updatedAccount;
    }

    public void deleteAccountById(Long id) {
        repository.deleteById(id);
    }
}
