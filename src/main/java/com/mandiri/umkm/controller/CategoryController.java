package com.mandiri.umkm.controller;

import com.mandiri.umkm.dto.request.CategoryRequest;
import com.mandiri.umkm.dto.response.CategoryResponse;
import com.mandiri.umkm.dto.response.CommonResponse;
import com.mandiri.umkm.dto.response.CommonWithPagingResponse;
import com.mandiri.umkm.service.CategoryService;
import com.mandiri.umkm.utils.ResponseUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@Tag(name = "Category UMKM", description = "APIs for managing category")
public class CategoryController {

    private final CategoryService categoryService;

    private static class CommonResponseCategoryResponse extends CommonResponse<CategoryResponse> {
    }

    private static class CommonResponseCategoryListResponse extends CommonWithPagingResponse<List<CategoryResponse>> {
    }

    @Operation(summary = "Create Category", description = "Create a new category",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Successfully created category", content = @Content(schema = @Schema(implementation = CommonResponseCategoryResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
            })
    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody CategoryRequest category) {
        categoryService.create(category);
        return ResponseUtils.buildCommonResponse(HttpStatus.CREATED, "Category created successfully", null);
    }

    @Operation(summary = "Get Category by ID", description = "Retrieve a category by its ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved category", content = @Content(schema = @Schema(implementation = CommonResponseCategoryResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Category not found", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
            })
    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable String id) {
        CategoryResponse categoryResponse = categoryService.getById(id);
        return ResponseUtils.buildCommonResponse(HttpStatus.OK, "Category found", categoryResponse);
    }

    @Operation(summary = "Update Category", description = "Update an existing category by ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully updated category", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
            })
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable String id, @RequestBody CategoryRequest categoryRequest) {
        categoryService.update(id, categoryRequest);
        return ResponseUtils.buildCommonResponse(HttpStatus.OK, "Category updated successfully", null);
    }

    @Operation(summary = "Delete Category", description = "Delete a category by ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully deleted category", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Category not found", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
            })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable String id) {
        categoryService.delete(id);
        return ResponseUtils.buildCommonResponse(HttpStatus.OK, "Category deleted successfully", null);
    }

    @Operation(summary = "Get All Categories", description = "Retrieve all categories with pagination",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved categories", content = @Content(schema = @Schema(implementation = CommonResponseCategoryListResponse.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
            })
    @GetMapping("")
    public ResponseEntity<?> getAllCategoriesPaged(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<CategoryResponse> categories = categoryService.getAll((page - 1), size);
        return ResponseUtils.buildResponsePage(HttpStatus.OK, "Success get all categories", categories);
    }
}
