package tech.nightsky.budgetly.core.api;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import tech.nightsky.budgetly.core.util.ErrorUtil;
import tech.nightsky.budgetly.core.exception.NotFoundException;

import java.util.Collections;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerController {

    @Hidden
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex, WebRequest request) {
        log.error(ex.getLocalizedMessage(), ex);

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(
                        ErrorResponse.builder()
                                .message(ex.getMessage())
                                .error(ErrorUtil.exToError(ex.getClass().getSimpleName()))
                                .code(HttpStatus.NOT_FOUND.value())
                                .details(Collections.emptyList())
                                .build()
                );
    }
}
