package tech.nightsky.budgetly.exception;

public class ErrorMessage {
    public static final String NOT_FOUND_REASON = "Entity not Found.";

    public static String notFoundMessage(Long id) {
        return "Сущность с идентификатором: " + id + " не найдена!";
    }

}
