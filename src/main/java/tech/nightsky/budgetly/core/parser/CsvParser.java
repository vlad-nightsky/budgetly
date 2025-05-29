package tech.nightsky.budgetly.core.parser;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.function.Function;

@Slf4j
public class CsvParser {
    public static String TYPE = "text/csv";

    public static boolean hasCsvFormat(MultipartFile file) {
        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    @Builder
    public static Parser csvBuilder(@NonNull MultipartFile file, String delimiter) {
        val _delimiter = !StringUtils.hasText(delimiter) ? delimiter : ";";
        try (
                var csvParser = CSVParser.parse(
                        file.getInputStream(),
                        StandardCharsets.UTF_8,
                        CSVFormat.DEFAULT
                                .builder()
                                .setHeader()
                                .setDelimiter(_delimiter)
                                .get())
        ) {
            return Parser.of(csvParser);
        } catch (IOException e) {
            throw new RuntimeException("CSV data is failed to parse: " + e.getMessage());
        }
    }

    @RequiredArgsConstructor(access = AccessLevel.PRIVATE, staticName = "of")
    public static class Parser {
        private final CSVParser csvParser;

        public <T> List<T> parse(Function<CSVRecord, T> parser) {
            return csvParser.getRecords()
                    .stream()
                    .map(parser)
                    .toList();
        }
    }

}
