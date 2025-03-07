package tech.nightsky.budgetly.exception;


public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }

    public static NotFoundException of(Long id) {
        return new NotFoundException(ErrorMessage.notFoundMessage(id));
    }
}
