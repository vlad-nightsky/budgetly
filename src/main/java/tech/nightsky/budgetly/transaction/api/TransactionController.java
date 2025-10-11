package tech.nightsky.budgetly.transaction.api;

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
import tech.nightsky.budgetly.core.ToRefactoringDocs;
import tech.nightsky.budgetly.core.api.ErrorResponse;
import tech.nightsky.budgetly.core.api.Route;
import tech.nightsky.budgetly.core.exception.NotFoundException;
import tech.nightsky.budgetly.transaction.TransactionRequest;
import tech.nightsky.budgetly.transaction.TransactionService;

import java.util.List;

//todo добавить валидации в модели реквестов (dto)
@Validated
@RestController
@RequestMapping(Route.TRANSACTIONS)
@RequiredArgsConstructor
@Tag(name = ToRefactoringDocs.TransactionController.NAME, description = ToRefactoringDocs.TransactionController.DESCRIPTION)
public class TransactionController {
    private final TransactionService service;
    private final TransactionControllerMapper mapper;

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = ToRefactoringDocs.TransactionController.GetAll.SUMMARY, description = ToRefactoringDocs.TransactionController.GetAll.DESCRIPTION)
    @ApiResponse(responseCode = "200", description = ToRefactoringDocs.TransactionController.GetAll.MESSAGE)
    public ResponseEntity<List<TransactionResponse>> getAllTransactions() {
        return ResponseEntity.ok().body(service.getAllTransactions().stream().map(mapper::map).toList());
    }

    @Operation(summary = ToRefactoringDocs.TransactionController.GetById.SUMMARY, description = ToRefactoringDocs.TransactionController.GetById.DESCRIPTION)
    @ApiResponse(responseCode = "200", description = ToRefactoringDocs.TransactionController.GetById.SUCCESS_MESSAGE)
    @ApiResponse(responseCode = "404", description = ToRefactoringDocs.TransactionController.GetById.NOT_FOUND_MESSAGE,
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @GetMapping(path = Route.ID, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionResponse> getTransactionById(@Parameter(ref = "#/components/parameters/PathId") @PathVariable Long id) {
        return ResponseEntity.ok().body(
                service.getTransactionById(id).map(mapper::map)
                        .orElseThrow(() -> NotFoundException.of(id))
        );
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = ToRefactoringDocs.TransactionController.Save.SUMMARY, description = ToRefactoringDocs.TransactionController.Save.DESCRIPTION)
    @ApiResponse(responseCode = "201", description = ToRefactoringDocs.TransactionController.Save.SUCCESS_MESSAGE,
            headers = {@Header(name = "Location", ref = "#/components/headers/Location")})
    public ResponseEntity<TransactionResponse> saveTransaction(@RequestBody TransactionRequest request) {

        val transaction = service.saveTransaction(request);

        val location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path(Route.ID)
                .buildAndExpand(transaction.id())
                .toUri();

        val response = mapper.map(transaction);

        return ResponseEntity
                .created(location)
                .body(response);
    }

    @Operation(summary = ToRefactoringDocs.TransactionController.Update.SUMMARY, description = ToRefactoringDocs.TransactionController.Update.DESCRIPTION)
    @ApiResponse(responseCode = "200", description = ToRefactoringDocs.TransactionController.Update.SUCCESS_MESSAGE)
    @PutMapping(path = Route.ID, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionResponse> updateTransaction(@Parameter(ref = "#/components/parameters/PathId") @PathVariable Long id, @RequestBody TransactionRequest transactionDto) {
        return ResponseEntity.ok().body(mapper.map(service.updateTransaction(id, transactionDto)));
    }

    @DeleteMapping(path = Route.ID, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = ToRefactoringDocs.TransactionController.Delete.SUMMARY, description = ToRefactoringDocs.TransactionController.Delete.DESCRIPTION)
    @ApiResponse(responseCode = "204", description = ToRefactoringDocs.TransactionController.Delete.SUCCESS_MESSAGE)
    public ResponseEntity<Void> deleteTransactionById(@Parameter(ref = "#/components/parameters/PathId") @PathVariable Long id) {
        service.deleteTransactionById(id);
        return ResponseEntity.noContent().build();
    }
}