package tech.nightsky.budgetly.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tech.nightsky.budgetly.dto.TransactionDto;
import tech.nightsky.budgetly.model.Transaction;
import tech.nightsky.budgetly.service.TransactionService;

import java.util.List;

//todo добавить валидации в модели реквестов (dto)
@Validated
@RestController
@RequestMapping(Route.TRANSACTIONS)
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService service;

    @GetMapping()
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        return ResponseEntity.ok().body(service.getAllTransactions());
    }

    @GetMapping(Route.ID)
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id) {
        //todo возвращать полноценную ошибку
        return ResponseEntity.ok().body(
                service.getTransactionById(id).orElse(null)
        );
    }

    //todo Добавить документацию
    @PostMapping()
    public ResponseEntity<Transaction> saveTransaction(@RequestBody TransactionDto transactionDto) {
        return ResponseEntity.ok().body(service.saveTransaction(transactionDto));
    }

    @PutMapping(Route.ID)
    public ResponseEntity<Transaction> updateTransaction(@PathVariable Long id, @RequestBody TransactionDto transactionDto) {
        return ResponseEntity.ok().body(service.updateTransaction(id, transactionDto));
    }

    //todo сделать единую систему ответа
    @DeleteMapping(Route.ID)
    public ResponseEntity<String> deleteTransactionById(@PathVariable Long id) {
        service.deleteTransactionById(id);
        return ResponseEntity.ok().body("Транзакция удалёна успешно");
    }
}