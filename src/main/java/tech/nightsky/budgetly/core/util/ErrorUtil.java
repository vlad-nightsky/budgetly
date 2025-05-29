package tech.nightsky.budgetly.core.util;

/**
 * Утилитный класс для работы с ошибками и исключениями.
 * <p>
 * Этот класс предоставляет статические методы для преобразования строк, связанных с исключениями.
 */
public class ErrorUtil {

    /**
     * Удаляет суффикс "Exception" из переданного имени класса, если он присутствует.
     * <p>
     * Этот метод полезен, когда необходимо преобразовать имя класса исключения в более короткое
     * название ошибки, убрав стандартный суффикс "Exception".
     *
     * @param className имя класса, которое может заканчиваться на "Exception"
     * @return имя класса без суффикса "Exception", если он присутствовал;
     * иначе возвращается исходное имя класса
     * @throws NullPointerException если переданный аргумент {@code className} равен {@code null}
     * @example <pre>
     * {@code
     * String result1 = ErrorUtils.exToError("MyCustomException"); // вернет "MyCustom"
     * String result2 = ErrorUtils.exToError("AnotherException"); // вернет "Another"
     * String result3 = ErrorUtils.exToError("NoSuffixHere");    // вернет "NoSuffixHere"
     * }
     * </pre>
     */
    public static String exToError(String className) {
        if (className.endsWith("Exception"))
            return className.substring(0, className.length() - 9);
        return className;
    }
}
