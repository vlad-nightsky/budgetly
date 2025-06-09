package tech.nightsky.budgetly.account.api;

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
import tech.nightsky.budgetly.account.AccountService;
import tech.nightsky.budgetly.core.ToRefactoringDocs;
import tech.nightsky.budgetly.core.api.ErrorResponse;
import tech.nightsky.budgetly.core.api.Route;
import tech.nightsky.budgetly.core.exception.NotFoundException;

import java.util.List;

@Validated
@RestController
@RequestMapping(Route.ACCOUNTS)
@RequiredArgsConstructor
@Tag(name = ToRefactoringDocs.AccountController.TITLE, description = ToRefactoringDocs.AccountController.DESCRIPTION)
public class AccountController {

    private final AccountService service;
    private final AccountControllerMapper mapper;

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = ToRefactoringDocs.AccountController.GetAll.SUMMARY, description = ToRefactoringDocs.AccountController.GetAll.DESCRIPTION)
    @ApiResponse(responseCode = "200", description = ToRefactoringDocs.AccountController.GetAll.MESSAGE)
    public ResponseEntity<List<AccountResponse>> getAllAccounts() {
        return ResponseEntity.ok().body(service.getAllAccounts().stream().map(mapper::map).toList());
    }

    @Operation(summary = ToRefactoringDocs.AccountController.GetById.SUMMARY, description = ToRefactoringDocs.AccountController.GetById.DESCRIPTION)
    @ApiResponse(responseCode = "200", description = ToRefactoringDocs.AccountController.GetById.SUCCESS_MESSAGE,
            content = @Content(schema = @Schema(implementation = AccountResponse.class)))
    @ApiResponse(responseCode = "404", description = ToRefactoringDocs.AccountController.GetById.NOT_FOUND_MESSAGE,
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @GetMapping(path = Route.ID, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountResponse> getAccountById(@Parameter(ref = "#/components/parameters/PathId") @PathVariable Long id) {
        return ResponseEntity.ok().body(
                service.getAccountById(id).map(mapper::map)
                        .orElseThrow(() -> NotFoundException.of(id))
        );
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = ToRefactoringDocs.AccountController.Save.SUMMARY, description = ToRefactoringDocs.AccountController.Save.DESCRIPTION)
    @ApiResponse(responseCode = "201", description = ToRefactoringDocs.AccountController.Save.SUCCESS_MESSAGE,
            headers = {@Header(name = "Location", ref = "#/components/headers/Location")})
    public ResponseEntity<AccountResponse> saveAccount(@RequestBody AccountRequest request) {
        val account = service.createAccount(request);

        val location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path(Route.ID)
                .buildAndExpand(account.id())
                .toUri();

        val response = mapper.map(account);

        return ResponseEntity
                .created(location)
                .body(response);
    }

    @Operation(summary = ToRefactoringDocs.AccountController.Update.SUMMARY, description = ToRefactoringDocs.AccountController.Update.DESCRIPTION)
    @ApiResponse(responseCode = "200", description = ToRefactoringDocs.AccountController.Update.SUCCESS_MESSAGE,
            content = @Content(schema = @Schema(implementation = AccountResponse.class)))
    @PutMapping(path = Route.ID, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountResponse> updateAccount(@Parameter(ref = "#/components/parameters/PathId") @PathVariable Long id, @RequestBody AccountRequest request) {
        return ResponseEntity.ok().body(
                mapper.map(service.updateAccount(id, request)));
    }

    @DeleteMapping(path = Route.ID, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = ToRefactoringDocs.AccountController.Delete.SUMMARY, description = ToRefactoringDocs.AccountController.Delete.DESCRIPTION)
    @ApiResponse(responseCode = "204", description = ToRefactoringDocs.AccountController.Delete.SUCCESS_MESSAGE)
    public ResponseEntity<Void> deleteAccountById(@Parameter(ref = "#/components/parameters/PathId") @PathVariable Long id) {
        service.deleteAccountById(id);
        return ResponseEntity.noContent().build();
    }
}