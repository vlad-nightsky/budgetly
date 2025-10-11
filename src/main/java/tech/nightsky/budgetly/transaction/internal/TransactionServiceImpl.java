package tech.nightsky.budgetly.transaction.internal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tech.nightsky.budgetly.account.AccountService;
import tech.nightsky.budgetly.account.AccountSummary;
import tech.nightsky.budgetly.category.CategoryService;
import tech.nightsky.budgetly.category.CategorySummary;
import tech.nightsky.budgetly.core.exception.NotFoundException;
import tech.nightsky.budgetly.transaction.TransactionRequest;
import tech.nightsky.budgetly.transaction.TransactionService;
import tech.nightsky.budgetly.transaction.TransactionSummary;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Сервис обработки транзакций.
 * <p>
 * Бизнес логика
 */
@Slf4j
@Service
@RequiredArgsConstructor
class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository repository;
    private final AccountService accountService;
    private final CategoryService categoryService;
    private final TransactionMapper mapper;

    @Override
    public List<TransactionSummary> getAllTransactions() {
        return repository.findAll().stream().map(mapper::map).collect(Collectors.toList());
    }

    @Override
    public Optional<TransactionSummary> getTransactionById(Long id) {
        return repository.findById(id).map(mapper::map);
    }

    @Override
    public TransactionSummary saveTransaction(TransactionRequest transactionDto) {
        //todo сделать final
        AccountSummary account = accountService.getAccountById(transactionDto.accountId())
                //todo корректная система ошибок
                .orElseThrow(() -> new IllegalArgumentException("Account no found"));

        CategorySummary category = categoryService.getCategoryById(transactionDto.categoryId())
                //todo корректная система ошибок
                .orElseThrow(() -> new IllegalArgumentException("Category no found"));

        Transaction newTransaction = Transaction.builder()
                .accountId(account.id())
                .categoryId(category.id())
                .amount(transactionDto.amount())
                .description(transactionDto.description())
                .type(transactionDto.type())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Transaction savedTransaction = repository.save(newTransaction);

        log.info("Транзакция с идентификатором: {} сохранена успешно", newTransaction.getId());
        return mapper.map(savedTransaction);
    }

    @Override
    public TransactionSummary updateTransaction(Long id, TransactionRequest request) {
        Transaction existingTransaction = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Transaction no found"));

        existingTransaction.setUpdatedAt(LocalDateTime.now());

        AccountSummary account = accountService.getAccountById(request.accountId())
                //todo корректная система ошибок
                .orElseThrow(() -> NotFoundException.of(request.accountId()));

        CategorySummary category = categoryService.getCategoryById(request.categoryId())
                //todo корректная система ошибок
                .orElseThrow(() -> NotFoundException.of(request.categoryId()));

        existingTransaction.setDescription(request.description());
        existingTransaction.setAmount(request.amount());
        existingTransaction.setType(request.type());
        existingTransaction.setCategoryId(category.id());
        existingTransaction.setAccountId(account.id());

        Transaction updatedTransaction = repository.save(existingTransaction);

        log.info("Транзакция с идентификатором: {} обновлёна успешно", updatedTransaction.getId());
        return mapper.map(updatedTransaction);
    }

    @Override
    public void deleteTransactionById(Long id) {
        repository.deleteById(id);
    }
}
