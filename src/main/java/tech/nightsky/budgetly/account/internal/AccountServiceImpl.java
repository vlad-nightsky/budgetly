package tech.nightsky.budgetly.account.internal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.nightsky.budgetly.account.AccountCreated;
import tech.nightsky.budgetly.account.AccountService;
import tech.nightsky.budgetly.account.AccountSummary;
import tech.nightsky.budgetly.account.api.AccountRequest;
import tech.nightsky.budgetly.core.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
class AccountServiceImpl implements AccountService {
    private final AccountRepository repository;
    private final AccountMapper mapper;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public List<AccountSummary> getAllAccounts() {
        return repository.findAll().stream().map(mapper::map).collect(Collectors.toList());
    }

    @Override
    public Optional<AccountSummary> getAccountById(Long id) {
        return repository.findById(id).map(mapper::map);
    }

    //todo добавить хеширования пароля и простую авторизацию
    @Override
    @Transactional
    public AccountSummary createAccount(AccountRequest request) {
        val account = Account.builder()
                .username(request.username())
                .password(request.password())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        val savedAccount = repository.save(account);

        eventPublisher.publishEvent(new AccountCreated(mapper.map(account)));

        log.info("Пользователь с идентификатором: {} сохранён успешно", account.getId());
        return mapper.map(savedAccount);
    }

    @Override
    public AccountSummary updateAccount(Long id, AccountRequest request) {
        val existingAccount = repository.findById(id)
                .orElseThrow(() -> NotFoundException.of(id));

        existingAccount.setUsername(request.username());
        existingAccount.setPassword(request.password());
        existingAccount.setUpdatedAt(LocalDateTime.now());

        val updatedAccount = repository.save(existingAccount);

        log.info("Пользователь с идентификатором: {} обновлён успешно", existingAccount.getId());
        return mapper.map(updatedAccount);
    }

    @Override
    public void deleteAccountById(Long id) {
        repository.deleteById(id);
    }
}
