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
import tech.nightsky.budgetly.dto.request.TransactionRequest;
import tech.nightsky.budgetly.dto.response.ErrorResponse;
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
@Tag(name = Docs.TransactionController.NAME, description = Docs.TransactionController.DESCRIPTION)
public class TransactionController {

    private final TransactionService service;
    private final ResponseMapper mapper;

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = Docs.TransactionController.GetAll.SUMMARY, description = Docs.TransactionController.GetAll.DESCRIPTION)
    @ApiResponse(responseCode = "200", description = Docs.TransactionController.GetAll.MESSAGE)
    public ResponseEntity<List<TransactionResponse>> getAllTransactions() {
        return ResponseEntity.ok().body(service.getAllTransactions().stream().map(mapper::map).toList());
    }

    @Operation(summary = Docs.TransactionController.GetById.SUMMARY, description = Docs.TransactionController.GetById.DESCRIPTION)
    @ApiResponse(responseCode = "200", description = Docs.TransactionController.GetById.SUCCESS_MESSAGE)
    @ApiResponse(responseCode = "404", description = Docs.TransactionController.GetById.NOT_FOUND_MESSAGE,
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @GetMapping(path = Route.ID, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionResponse> getTransactionById(@Parameter(ref = "#/components/parameters/PathId") @PathVariable Long id) {
        return ResponseEntity.ok().body(
                service.getTransactionById(id).map(mapper::map)
                        .orElseThrow(() -> NotFoundException.of(id))
        );
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = Docs.TransactionController.Save.SUMMARY, description = Docs.TransactionController.Save.DESCRIPTION)
    @ApiResponse(responseCode = "201", description = Docs.TransactionController.Save.SUCCESS_MESSAGE,
            headers = {@Header(name = "Location", ref = "#/components/headers/Location")})
    public ResponseEntity<TransactionResponse> saveTransaction(@RequestBody TransactionRequest request) {

        val transaction = service.saveTransaction(request);

        val location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path(Route.ID)
                .buildAndExpand(transaction.getId())
                .toUri();

        val response = mapper.map(transaction);

        return ResponseEntity
                .created(location)
                .body(response);
    }

    @Operation(summary = Docs.TransactionController.Update.SUMMARY, description = Docs.TransactionController.Update.DESCRIPTION)
    @ApiResponse(responseCode = "200", description = Docs.TransactionController.Update.SUCCESS_MESSAGE)
    @PutMapping(path = Route.ID, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionResponse> updateTransaction(@Parameter(ref = "#/components/parameters/PathId") @PathVariable Long id, @RequestBody TransactionRequest transactionDto) {
        return ResponseEntity.ok().body(mapper.map(service.updateTransaction(id, transactionDto)));
    }

    @DeleteMapping(path = Route.ID, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = Docs.TransactionController.Delete.SUMMARY, description = Docs.TransactionController.Delete.DESCRIPTION)
    @ApiResponse(responseCode = "204", description = Docs.TransactionController.Delete.SUCCESS_MESSAGE)
    public ResponseEntity<Void> deleteTransactionById(@Parameter(ref = "#/components/parameters/PathId") @PathVariable Long id) {
        service.deleteTransactionById(id);
        return ResponseEntity.noContent().build();
    }
}