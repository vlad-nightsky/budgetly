package tech.nightsky.budgetly.core.parser;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
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
    @Builder
    public static Parser csvBuilder(@NonNull MultipartFile file, String delimiter) {
        final var _delimiter = StringUtils.hasText(delimiter) ? delimiter : ";";
        try {
            try (var csvParser = CSVParser.parse(
                    file.getInputStream(),
                    StandardCharsets.UTF_8,
                    CSVFormat.DEFAULT
                            .builder()
                            .setHeader()
                            .setSkipHeaderRecord(true)
                            .setQuote('"')
                            .setTrim(true)
                            .setIgnoreEmptyLines(true)
                            .setDelimiter(_delimiter)
                            .get())) {
                return Parser.of(csvParser.getRecords());
            }
        } catch (IOException e) {
            throw new RuntimeException("CSV data is failed to parse: " + e.getMessage());
        }
    }

    @RequiredArgsConstructor(access = AccessLevel.PRIVATE, staticName = "of")
    public static class Parser {
        private final List<CSVRecord> records;

        public <T> List<T> parse(Function<CSVRecord, T> parser) {
            return records
                    .stream()
                    .map(parser)
                    .toList();
        }
    }

}
