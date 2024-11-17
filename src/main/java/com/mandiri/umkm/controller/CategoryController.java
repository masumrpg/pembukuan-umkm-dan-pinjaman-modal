package com.mandiri.umkm.controller;

import com.mandiri.umkm.dto.request.CategoryRequest;
import com.mandiri.umkm.dto.response.CategoryResponse;
import com.mandiri.umkm.service.CategoryService;
import com.mandiri.umkm.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody CategoryRequest category) {
        categoryService.create(category);
        return ResponseUtils.buildCommonResponse(HttpStatus.CREATED, "Category created successfully", null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable String id) {
        CategoryResponse categoryResponse = categoryService.getById(id);
        return ResponseUtils.buildCommonResponse(HttpStatus.OK, "Category found", categoryResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable String id, @RequestBody CategoryRequest categoryRequest) {
        categoryService.update(id, categoryRequest);
        return ResponseUtils.buildCommonResponse(HttpStatus.OK, "Category updated successfully", null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable String id) {
        categoryService.delete(id);
        return ResponseUtils.buildCommonResponse(HttpStatus.OK, "Category deleted successfully", null);
    }

    @GetMapping("")
    public ResponseEntity<?> getAllCategoriesPaged(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<CategoryResponse> categories = categoryService.getAll((page - 1), size);
        return ResponseUtils.buildResponsePage(HttpStatus.OK, "Success get all categories", categories);
    }
}
