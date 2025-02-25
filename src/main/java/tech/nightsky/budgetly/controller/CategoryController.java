package tech.nightsky.budgetly.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tech.nightsky.budgetly.dto.request.CategoryRequest;
import tech.nightsky.budgetly.dto.response.CategoryResponse;
import tech.nightsky.budgetly.exception.NotFoundException;
import tech.nightsky.budgetly.mapper.ResponseMapper;
import tech.nightsky.budgetly.service.CategoryService;

import java.util.List;

@Validated
@RestController
@RequestMapping(Route.CATEGORIES)
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;
    private final ResponseMapper mapper;

    @GetMapping()
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        return ResponseEntity.ok().body(service.getAllCategories().stream().map(mapper::map).toList());
    }

    @GetMapping(Route.ID)
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable Long id) {
        //todo возвращать полноценную ошибку
        return ResponseEntity.ok().body(
                service.getCategoryById(id).map(mapper::map)
                        .orElseThrow(() -> NotFoundException.of(id))
        );
    }

    //todo Добавить документацию
    @PostMapping()
    public ResponseEntity<CategoryResponse> saveCategory(@RequestBody CategoryRequest request) {
        return ResponseEntity.ok().body(mapper.map(service.saveCategory(request)));
    }

    @PutMapping(Route.ID)
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable Long id, @RequestBody CategoryRequest request) {
        return ResponseEntity.ok().body(mapper.map(service.updateCategory(id, request)));
    }

    //todo сделать единую систему ответа
    @DeleteMapping(Route.ID)
    public ResponseEntity<String> deleteCategoryById(@PathVariable Long id) {
        service.deleteCategoryById(id);
        return ResponseEntity.ok().body("Категория удалёна успешно");
    }
}