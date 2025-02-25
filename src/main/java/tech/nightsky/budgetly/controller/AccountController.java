package tech.nightsky.budgetly.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tech.nightsky.budgetly.model.Account;
import tech.nightsky.budgetly.service.AccountService;

import java.util.List;

@Validated
@RestController
@RequestMapping(Route.ACCOUNTS)
@RequiredArgsConstructor
public class AccountController {

    private final AccountService service;

    @GetMapping("/")
    public ResponseEntity<List<Account>> getAllAccounts() {
        return ResponseEntity.ok().body(service.getAllAccounts());
    }

    //todo вынести в route
    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
        //todo возвращать полноценную ошибку
        return ResponseEntity.ok().body(
                service.getAccountById(id).orElse(null)
        );
    }

    //todo использовать вместо сущности dto
    //todo Добавить документацию
    @PostMapping("/")
    public ResponseEntity<Account> saveAccount(@RequestBody Account employee) {
        return ResponseEntity.ok().body(service.saveAccount(employee));
    }

    //todo добавить корректную систему обновления
    @PutMapping("/")
    public ResponseEntity<Account> updateAccount(@RequestBody Account employee) {
        return ResponseEntity.ok().body(service.updateAccount(employee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccountById(@PathVariable Long id) {
        service.deleteAccountById(id);
        return ResponseEntity.ok().body("Пользователь удалён успешно");
    }
}