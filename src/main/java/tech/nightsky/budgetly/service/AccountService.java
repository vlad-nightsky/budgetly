package tech.nightsky.budgetly.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
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

    public Account getAccountById(Long id) {
        Optional<Account> optionalAccount = repository.findById(id);
        if (optionalAccount.isPresent()) {
            return optionalAccount.get();
        }
        log.info("Пользователь с идентификатором: {} не существует", id);
        return null;
    }

    //todo добавить документацию
    //добавить хеширования пароля и простую авторизацию
    public Account saveAccount(Account employee) {
        employee.setCreatedAt(LocalDateTime.now());
        employee.setUpdatedAt(LocalDateTime.now());
        Account savedAccount = repository.save(employee);

        log.info("Пользователь с идентификатором: {} сохранён успешно", employee.getId());
        return savedAccount;
    }

    public Account updateAccount(Account employee) {
        Optional<Account> existingAccount = repository.findById(employee.getId());
        employee.setCreatedAt(existingAccount.get().getCreatedAt());
        employee.setUpdatedAt(LocalDateTime.now());

        Account updatedAccount = repository.save(employee);

        log.info("Пользователь с идентификатором: {} обновлён успешно", employee.getId());
        return updatedAccount;
    }

    public void deleteAccountById(Long id) {
        repository.deleteById(id);
    }
}
