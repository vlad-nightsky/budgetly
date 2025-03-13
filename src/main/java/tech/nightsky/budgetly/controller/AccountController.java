package tech.nightsky.budgetly.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.nightsky.budgetly.dto.request.AccountRequest;
import tech.nightsky.budgetly.dto.response.AccountResponse;
import tech.nightsky.budgetly.dto.response.ErrorResponse;
import tech.nightsky.budgetly.exception.NotFoundException;
import tech.nightsky.budgetly.mapper.ResponseMapper;
import tech.nightsky.budgetly.service.AccountService;

import java.util.List;

@Validated
@RestController
@RequestMapping(Route.ACCOUNTS)
@RequiredArgsConstructor
@Tag(name = "Управление аккаунтами", description = "Операции по управлению аккаунтами пользователя")
public class AccountController {

    private final AccountService service;
    private final ResponseMapper mapper;

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Список аккаунтов пользователей", description = "Возвращает список аккаунтов пользователей")
    @ApiResponse(responseCode = "200", description = "Получен список аккаунтов пользователей")
    public ResponseEntity<List<AccountResponse>> getAllAccounts() {
        return ResponseEntity.ok().body(service.getAllAccounts().stream().map(mapper::map).toList());
    }

    @Operation(summary = "Информация о аккаунте", description = "Запрос на получение информации о аккаунте пользователя.")
    @ApiResponse(responseCode = "200", description = "Аккаунт найден",
            content = @Content(schema = @Schema(implementation = AccountResponse.class)))
    @ApiResponse(responseCode = "404", description = "Аккаунт не найден",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @GetMapping(path = Route.ID, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountResponse> getAccountById(@Parameter(ref = "#/components/parameters/PathId") @PathVariable Long id) {
        return ResponseEntity.ok().body(
                service.getAccountById(id).map(mapper::map)
                        .orElseThrow(() -> NotFoundException.of(id))
        );
    }

    //todo Добавить документацию
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Создание аккаунта", description = "Запрос на создание аккаунта пользователя. В случае успеха вернёт данные по аккаунту.")
    @ApiResponse(responseCode = "201", description = "Аккаунт успешно создан",
            headers = {@Header(name = "Location", ref = "#/components/headers/Location")})
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

    @Operation(summary = "Обновление аккаунта", description = "Запрос на обновление аккаунта пользователя. В случае успеха вернёт данные по аккаунту.")
    @ApiResponse(responseCode = "200", description = "Аккаунт успешно создан",
            content = @Content(schema = @Schema(implementation = AccountResponse.class))
    )
    @PutMapping(path = Route.ID, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountResponse> updateAccount(@Parameter(ref = "#/components/parameters/PathId") @PathVariable Long id, @RequestBody AccountRequest request) {
        return ResponseEntity.ok().body(
                mapper.map(service.updateAccount(id, request)));
    }

    @DeleteMapping(path = Route.ID, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Удаление аккаунта", description = "Запрос на удаление аккаунта пользователя. Аккаунт удаляется безвозвратно, восстановить его нельзя.")
    @ApiResponse(responseCode = "204", description = "Аккаунт успешно удалён. Нечего возвращать.")
    public ResponseEntity<Void> deleteAccountById(@Parameter(ref = "#/components/parameters/PathId") @PathVariable Long id) {
        service.deleteAccountById(id);
        return ResponseEntity.noContent().build();
    }
}