package tech.nightsky.budgetly.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import tech.nightsky.budgetly.dto.response.ErrorResponse;
import tech.nightsky.budgetly.exception.ErrorUtils;
import tech.nightsky.budgetly.exception.NotFoundException;

import java.util.Collections;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex, WebRequest request) {
        log.error(ex.getLocalizedMessage(), ex);

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(
                        ErrorResponse.builder()
                                .message(ex.getMessage())
                                .error(ErrorUtils.exToError(ex.getClass().getSimpleName()))
                                .code(HttpStatus.NOT_FOUND.value())
                                .details(Collections.emptyList())
                                .build()
                );
    }
}
