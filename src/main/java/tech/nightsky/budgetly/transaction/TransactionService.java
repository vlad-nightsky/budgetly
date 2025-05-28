package tech.nightsky.budgetly.transaction;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tech.nightsky.budgetly.account.AccountService;
import tech.nightsky.budgetly.account.dto.AccountSummary;
import tech.nightsky.budgetly.category.CategoryService;
import tech.nightsky.budgetly.core.NotFoundException;
import tech.nightsky.budgetly.account.internal.Account;
import tech.nightsky.budgetly.category.internal.Category;
import tech.nightsky.budgetly.tbankTransaction.internal.TransactionRepository;
import tech.nightsky.budgetly.transaction.internal.Transaction;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Сервис обработки транзакций.
 * <p>
 * Бизнес логика
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository repository;
    private final AccountService accountService;
    private final CategoryService categoryService;

    public List<Transaction> getAllTransactions() {
        return repository.findAll();
    }

    public Optional<Transaction> getTransactionById(Long id) {
        return repository.findById(id);
    }

    public Transaction saveTransaction(TransactionRequest transactionDto) {
        //todo сделать final
        AccountSummary account = accountService.getAccountById(transactionDto.accountId())
                //todo корректная система ошибок
                .orElseThrow(() -> new IllegalArgumentException("Account no found"));

        Category category = categoryService.getCategoryById(transactionDto.categoryId())
                //todo корректная система ошибок
                .orElseThrow(() -> new IllegalArgumentException("Category no found"));

        Transaction newTransaction = Transaction.builder()
                .accountId(account.id())
                .category(category)
                .amount(transactionDto.amount())
                .description(transactionDto.description())
                .type(transactionDto.type())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Transaction savedTransaction = repository.save(newTransaction);

        log.info("Транзакция с идентификатором: {} сохранена успешно", newTransaction.getId());
        return savedTransaction;
    }

    public Transaction updateTransaction(Long id, TransactionRequest request) {
        Transaction existingTransaction = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Transaction no found"));

        existingTransaction.setUpdatedAt(LocalDateTime.now());

        AccountSummary account = accountService.getAccountById(request.accountId())
                //todo корректная система ошибок
                .orElseThrow(() -> NotFoundException.of(request.accountId()));

        Category category = categoryService.getCategoryById(request.categoryId())
                //todo корректная система ошибок
                .orElseThrow(() -> NotFoundException.of(request.categoryId()));

        existingTransaction.setDescription(request.description());
        existingTransaction.setAmount(request.amount());
        existingTransaction.setType(request.type());
        existingTransaction.setCategory(category);
        existingTransaction.setAccountId(account.id());

        Transaction updatedTransaction = repository.save(existingTransaction);

        log.info("Транзакция с идентификатором: {} обновлёна успешно", updatedTransaction.getId());
        return updatedTransaction;
    }

    public void deleteTransactionById(Long id) {
        repository.deleteById(id);
    }
}
