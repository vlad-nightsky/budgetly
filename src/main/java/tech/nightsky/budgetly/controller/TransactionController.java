package tech.nightsky.budgetly.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tech.nightsky.budgetly.dto.request.TransactionRequest;
import tech.nightsky.budgetly.dto.response.TransactionResponse;
import tech.nightsky.budgetly.exception.NotFoundException;
import tech.nightsky.budgetly.mapper.ResponseMapper;
import tech.nightsky.budgetly.service.TransactionService;

import java.util.List;

//todo добавить валидации в модели реквестов (dto)
@Validated
@RestController
@RequestMapping(Route.TRANSACTIONS)
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService service;
    private final ResponseMapper mapper;

    @GetMapping()
    public ResponseEntity<List<TransactionResponse>> getAllTransactions() {
        return ResponseEntity.ok().body(service.getAllTransactions().stream().map(mapper::map).toList());
    }

    @GetMapping(Route.ID)
    public ResponseEntity<TransactionResponse> getTransactionById(@PathVariable Long id) {
        //todo возвращать полноценную ошибку
        return ResponseEntity.ok().body(
                service.getTransactionById(id).map(mapper::map)
                        .orElseThrow(() -> NotFoundException.of(id))
        );
    }

    //todo Добавить документацию
    @PostMapping()
    public ResponseEntity<TransactionResponse> saveTransaction(@RequestBody TransactionRequest transactionDto) {
        return ResponseEntity.ok().body(mapper.map(service.saveTransaction(transactionDto)));
    }

    @PutMapping(Route.ID)
    public ResponseEntity<TransactionResponse> updateTransaction(@PathVariable Long id, @RequestBody TransactionRequest transactionDto) {
        return ResponseEntity.ok().body(mapper.map(service.updateTransaction(id, transactionDto)));
    }

    //todo сделать единую систему ответа
    @DeleteMapping(Route.ID)
    public ResponseEntity<String> deleteTransactionById(@PathVariable Long id) {
        service.deleteTransactionById(id);
        return ResponseEntity.ok().body("Транзакция удалёна успешно");
    }
}