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
import tech.nightsky.budgetly.doc.Docs;
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
@Tag(name = Docs.AccountController.NAME, description = Docs.AccountController.DESCRIPTION)
public class AccountController {

    private final AccountService service;
    private final ResponseMapper mapper;

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = Docs.AccountController.GetAll.SUMMARY, description = Docs.AccountController.GetAll.DESCRIPTION)
    @ApiResponse(responseCode = "200", description = Docs.AccountController.GetAll.MESSAGE)
    public ResponseEntity<List<AccountResponse>> getAllAccounts() {
        return ResponseEntity.ok().body(service.getAllAccounts().stream().map(mapper::map).toList());
    }

    @Operation(summary = Docs.AccountController.GetById.SUMMARY, description = Docs.AccountController.GetById.DESCRIPTION)
    @ApiResponse(responseCode = "200", description = Docs.AccountController.GetById.SUCCESS_MESSAGE,
            content = @Content(schema = @Schema(implementation = AccountResponse.class)))
    @ApiResponse(responseCode = "404", description = Docs.AccountController.GetById.NOT_FOUND_MESSAGE,
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @GetMapping(path = Route.ID, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountResponse> getAccountById(@Parameter(ref = "#/components/parameters/PathId") @PathVariable Long id) {
        return ResponseEntity.ok().body(
                service.getAccountById(id).map(mapper::map)
                        .orElseThrow(() -> NotFoundException.of(id))
        );
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = Docs.AccountController.Save.SUMMARY, description = Docs.AccountController.Save.DESCRIPTION)
    @ApiResponse(responseCode = "201", description = Docs.AccountController.Save.SUCCESS_MESSAGE,
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

    @Operation(summary = Docs.AccountController.Update.SUMMARY, description = Docs.AccountController.Update.DESCRIPTION)
    @ApiResponse(responseCode = "200", description = Docs.AccountController.Update.SUCCESS_MESSAGE,
            content = @Content(schema = @Schema(implementation = AccountResponse.class)))
    @PutMapping(path = Route.ID, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountResponse> updateAccount(@Parameter(ref = "#/components/parameters/PathId") @PathVariable Long id, @RequestBody AccountRequest request) {
        return ResponseEntity.ok().body(
                mapper.map(service.updateAccount(id, request)));
    }

    @DeleteMapping(path = Route.ID, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = Docs.AccountController.Delete.SUMMARY, description = Docs.AccountController.Delete.DESCRIPTION)
    @ApiResponse(responseCode = "204", description = Docs.AccountController.Delete.SUCCESS_MESSAGE)
    public ResponseEntity<Void> deleteAccountById(@Parameter(ref = "#/components/parameters/PathId") @PathVariable Long id) {
        service.deleteAccountById(id);
        return ResponseEntity.noContent().build();
    }
}