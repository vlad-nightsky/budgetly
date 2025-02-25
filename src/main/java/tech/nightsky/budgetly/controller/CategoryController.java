package tech.nightsky.budgetly.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import tech.nightsky.budgetly.dto.CategoryDto;
import tech.nightsky.budgetly.model.Category;
import tech.nightsky.budgetly.service.CategoryService;

import java.util.List;

@Validated
@RestController
@RequestMapping(Route.CATEGORIES)
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;

    @GetMapping("/")
    public ResponseEntity<List<Category>> getAllCategories() {
        return ResponseEntity.ok().body(service.getAllCategories());
    }

    //todo вынести в route
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        //todo возвращать полноценную ошибку
        return ResponseEntity.ok().body(
                service.getCategoryById(id).orElse(null)
        );
    }

    //todo Добавить документацию
    @PostMapping("/")
    public ResponseEntity<Category> saveCategory(@RequestBody CategoryDto categoryDto) {
        return ResponseEntity.ok().body(service.saveCategory(categoryDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto) {
        return ResponseEntity.ok().body(service.updateCategory(id, categoryDto));
    }

    //todo сделать единую систему ответа
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategoryById(@PathVariable Long id) {
        service.deleteCategoryById(id);
        return ResponseEntity.ok().body("Категория удалёна успешно");
    }
}