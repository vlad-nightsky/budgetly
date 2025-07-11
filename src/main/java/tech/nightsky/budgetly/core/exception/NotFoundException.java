package tech.nightsky.budgetly.core.exception;


import tech.nightsky.budgetly.core.api.ErrorMessage;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }

    public static NotFoundException of(Long id) {
        return new NotFoundException(ErrorMessage.notFoundMessage(id));
    }
}
