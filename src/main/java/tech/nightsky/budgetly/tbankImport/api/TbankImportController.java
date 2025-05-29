package tech.nightsky.budgetly.tbankImport.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import tech.nightsky.budgetly.core.ToRefactoringDocs;
import tech.nightsky.budgetly.core.api.Route;
import tech.nightsky.budgetly.tbankImport.ImportService;

@Validated
@RestController
@RequestMapping(Route.IMPORT)
@RequiredArgsConstructor
@Tag(name = ToRefactoringDocs.ImportController.TITLE, description = ToRefactoringDocs.ImportController.DESCRIPTION)
public class TbankImportController {
    private final ImportService service;
    private final TbankImportControllerMapper mapper;

    @PostMapping(value = "/csv", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = ToRefactoringDocs.ImportController.ImportCsv.SUMMARY, description = ToRefactoringDocs.ImportController.ImportCsv.DESCRIPTION)
    @ApiResponse(responseCode = "200", description = ToRefactoringDocs.ImportController.ImportCsv.MESSAGE)
    public ResponseEntity<TbankImportResponse> importCSV(@RequestParam("file") MultipartFile file) {
        // Проверка на пустой файл
        if (file.isEmpty()) {
            //todo вернуть корректную ошибку
            throw new RuntimeException("Please select a CSV file to upload");
        }

        return ResponseEntity.ok().body(mapper.map(service.importCsv(file)));
    }
}
