package tech.nightsky.budgetly.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tech.nightsky.budgetly.doc.Docs;
import tech.nightsky.budgetly.dto.response.TbankImportResponse;
import tech.nightsky.budgetly.mapper.TbankCsvMapper;
import tech.nightsky.budgetly.service.ImportService;

@Validated
@RestController
@RequestMapping(Route.IMPORT)
@RequiredArgsConstructor
@Tag(name = Docs.ImportController.TITLE, description = Docs.ImportController.DESCRIPTION)
public class ImportController {

    private final ImportService service;
    private final TbankCsvMapper tbankCsvMapper;


    @PostMapping(value = "/csv", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = Docs.ImportController.ImportCsv.SUMMARY, description = Docs.ImportController.ImportCsv.DESCRIPTION)
    @ApiResponse(responseCode = "200", description = Docs.ImportController.ImportCsv.MESSAGE)
    public ResponseEntity<TbankImportResponse> importCSV(@RequestParam("file") MultipartFile file) {
        // Проверка на пустой файл
        if (file.isEmpty()) {
            //todo вернуть корректную ошибку
            throw new RuntimeException("Please select a CSV file to upload");
        }

        return ResponseEntity.ok().body(tbankCsvMapper.map(service.importCsv(file)));
    }
}
