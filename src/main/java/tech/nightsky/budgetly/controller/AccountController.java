package tech.nightsky.budgetly.controller;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.nightsky.budgetly.dto.request.AccountRequest;
import tech.nightsky.budgetly.dto.response.AccountResponse;
import tech.nightsky.budgetly.exception.NotFoundException;
import tech.nightsky.budgetly.mapper.ResponseMapper;
import tech.nightsky.budgetly.service.AccountService;

import java.util.List;

@Validated
@RestController
@RequestMapping(Route.ACCOUNTS)
@RequiredArgsConstructor
public class AccountController {

    private final AccountService service;
    private final ResponseMapper mapper;

    @GetMapping()
    public ResponseEntity<List<AccountResponse>> getAllAccounts() {
        return ResponseEntity.ok().body(service.getAllAccounts().stream().map(mapper::map).toList());
    }

    @GetMapping(Route.ID)
    public ResponseEntity<AccountResponse> getAccountById(@PathVariable Long id) {
        //todo возвращать полноценную ошибку
        return ResponseEntity.ok().body(
                service.getAccountById(id).map(mapper::map)
                        .orElseThrow(() -> NotFoundException.of(id))
        );
    }

    //todo Добавить документацию
    @PostMapping()
    public ResponseEntity<AccountResponse> saveAccount(@RequestBody AccountRequest request) {
        val account = service.saveAccount(request);

        val location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path(Route.ID)
                .buildAndExpand(account.getId())
                .toUri();

        val response = mapper.map(account);

        return ResponseEntity
                .created(location)
                .body(response);
    }

    //todo добавить корректную систему обновления
    @PutMapping(Route.ID)
    public ResponseEntity<AccountResponse> updateAccount(@PathVariable Long id, @RequestBody AccountRequest request) {
        return ResponseEntity.ok().body(
                mapper.map(service.updateAccount(id, request)));
    }

    @DeleteMapping(Route.ID)
    public ResponseEntity<String> deleteAccountById(@PathVariable Long id) {
        service.deleteAccountById(id);
        return ResponseEntity.ok().body("Пользователь удалён успешно");
    }
}