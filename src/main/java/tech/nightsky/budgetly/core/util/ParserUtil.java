package tech.nightsky.budgetly.core.util;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static tech.nightsky.budgetly.core.Constant.RUSSIA;

@Slf4j
public class ParserUtil {
    public static DateTimeFormatter DATE_TIME = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
    public static DateTimeFormatter DATE = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static BigDecimal parseNumber(String number) {
        if (number == null || number.isBlank()) {
            return BigDecimal.ZERO;
        } else {
            try {
                return new BigDecimal(
                        NumberFormat.getInstance(RUSSIA)
                                .parse(number)
                                .toString()
                );
            } catch (ParseException e) {
                log.error(e.getMessage(), e);
                return BigDecimal.ZERO;
            }
        }
    }

    public static LocalDateTime parseDateTime(String date) {
        return LocalDateTime.parse(date, DATE_TIME);
    }

    public static LocalDate parseDate(String date) {
        return LocalDate.parse(date, DATE);
    }
}
