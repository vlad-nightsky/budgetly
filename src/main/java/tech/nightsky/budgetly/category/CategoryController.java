package tech.nightsky.budgetly.category;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.nightsky.budgetly.core.*;

import java.util.List;

@Validated
@RestController
@RequestMapping(Route.CATEGORIES)
@RequiredArgsConstructor
@Tag(name = ToRefactoringDocs.CategoryController.NAME, description = ToRefactoringDocs.CategoryController.DESCRIPTION)
public class CategoryController {

    private final CategoryService service;
    private final ToRefactoringMapper mapper;

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = ToRefactoringDocs.CategoryController.GetAll.SUMMARY, description = ToRefactoringDocs.CategoryController.GetAll.DESCRIPTION)
    @ApiResponse(responseCode = "200", description = ToRefactoringDocs.CategoryController.GetAll.MESSAGE)
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        return ResponseEntity.ok().body(service.getAllCategories().stream().map(mapper::map).toList());
    }

    @Operation(summary = ToRefactoringDocs.CategoryController.GetById.SUMMARY, description = ToRefactoringDocs.CategoryController.GetById.DESCRIPTION)
    @ApiResponse(responseCode = "200", description = ToRefactoringDocs.CategoryController.GetById.SUCCESS_MESSAGE)
    @ApiResponse(responseCode = "404", description = ToRefactoringDocs.CategoryController.GetById.NOT_FOUND_MESSAGE,
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    @GetMapping(path = Route.ID, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryResponse> getCategoryById(@Parameter(ref = "#/components/parameters/PathId") @PathVariable Long id) {
        return ResponseEntity.ok().body(
                service.getCategoryById(id).map(mapper::map)
                        .orElseThrow(() -> NotFoundException.of(id))
        );
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = ToRefactoringDocs.CategoryController.Save.SUMMARY, description = ToRefactoringDocs.CategoryController.Save.DESCRIPTION)
    @ApiResponse(responseCode = "201", description = ToRefactoringDocs.CategoryController.Save.SUCCESS_MESSAGE,
            headers = {@Header(name = "Location", ref = "#/components/headers/Location")})
    public ResponseEntity<CategoryResponse> saveCategory(@RequestBody CategoryRequest request) {
        val category = service.saveCategory(request);

        val location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path(Route.ID)
                .buildAndExpand(category.getId())
                .toUri();

        val response = mapper.map(category);

        return ResponseEntity
                .created(location)
                .body(response);
    }

    @Operation(summary = ToRefactoringDocs.CategoryController.Update.SUMMARY, description = ToRefactoringDocs.CategoryController.Update.DESCRIPTION)
    @ApiResponse(responseCode = "200", description = ToRefactoringDocs.CategoryController.Update.SUCCESS_MESSAGE)
    @PutMapping(path = Route.ID, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CategoryResponse> updateCategory(@Parameter(ref = "#/components/parameters/PathId") @PathVariable Long id, @RequestBody CategoryRequest request) {
        return ResponseEntity.ok().body(mapper.map(service.updateCategory(id, request)));
    }

    @DeleteMapping(path = Route.ID, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = ToRefactoringDocs.CategoryController.Delete.SUMMARY, description = ToRefactoringDocs.CategoryController.Delete.DESCRIPTION)
    @ApiResponse(responseCode = "204", description = ToRefactoringDocs.CategoryController.Delete.SUCCESS_MESSAGE)
    public ResponseEntity<Void> deleteCategoryById(@Parameter(ref = "#/components/parameters/PathId") @PathVariable Long id) {
        service.deleteCategoryById(id);
        return ResponseEntity.noContent().build();
    }
}