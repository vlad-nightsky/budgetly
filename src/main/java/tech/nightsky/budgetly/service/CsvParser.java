package tech.nightsky.budgetly.service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;
import tech.nightsky.budgetly.model.TbankTransaction;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static tech.nightsky.budgetly.model.Constant.RUSSIA;

@Slf4j
public class CsvParser {
    public static String TYPE = "text/csv";
    public static DateTimeFormatter DATE_TIME = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
    public static DateTimeFormatter DATE = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static boolean hasCsvFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public static List<TbankTransaction> csvToTransaction(InputStream is) {
        try (
                var csvParser = CSVParser.parse(
                        is,
                        StandardCharsets.UTF_8,
                        CSVFormat.DEFAULT
                                .builder()
                                .setHeader()
                                .setDelimiter(';')
                                .get())) {
            return csvParser.getRecords()
                    .stream()
                    .map(CsvParser::map)
                    .toList();
        } catch (IOException e) {
            throw new RuntimeException("CSV data is failed to parse: " + e.getMessage());
        }
    }

    @SneakyThrows
    private static TbankTransaction map(CSVRecord csvRecord) {
        return TbankTransaction.builder()
                .operationDate(LocalDateTime.parse(csvRecord.get("Дата операции"), DATE_TIME))
                .paymentDate(LocalDate.parse(csvRecord.get("Дата платежа"), DATE))
                .cardNumber(csvRecord.get("Номер карты"))
                .status(csvRecord.get("Статус"))
                .operationAmount(parseNumber(csvRecord.get("Сумма операции")))
                .operationCurrency(csvRecord.get("Валюта операции"))
                .paymentAmount(parseNumber(csvRecord.get("Сумма платежа")))
                .paymentCurrency(csvRecord.get("Валюта платежа"))
                .cashback(parseNumber(csvRecord.get("Кэшбэк")))
                .category(csvRecord.get("Категория"))
                .mcc(Integer.parseInt(csvRecord.get("MCC")))
                .description(csvRecord.get("Описание"))
                .bonuses(parseNumber(csvRecord.get("Бонусы (включая кэшбэк)")))
                .investmentRounding(parseNumber(csvRecord.get("Округление на инвесткопилку")))
                .roundedOperationAmount(parseNumber((csvRecord.get("Сумма операции с округлением"))))
                .build();
    }

    private static BigDecimal parseNumber(String number) {
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
}
