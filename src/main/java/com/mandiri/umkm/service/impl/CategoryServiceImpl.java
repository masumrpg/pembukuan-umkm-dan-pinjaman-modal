package com.mandiri.umkm.service.impl;

import com.mandiri.umkm.dto.request.CategoryRequest;
import com.mandiri.umkm.dto.response.CategoryResponse;
import com.mandiri.umkm.entity.Category;
import com.mandiri.umkm.repository.CategoryRepository;
import com.mandiri.umkm.service.CategoryService;
import com.mandiri.umkm.utils.MapperUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public void create(CategoryRequest request) {
        Category newCategory = Category.builder()
                .id(UUID.randomUUID().toString())
                .name(request.getName())
                .description(request.getDescription())
                .type(request.getType())
                .build();
        categoryRepository.create(newCategory);
    }

    @Override
    public CategoryResponse getById(String id) {
        return MapperUtils.toCategoryResponse(getOneById(id));
    }

    @Override
    public void update(String id, CategoryRequest categoryRequest) {
        Category category = getOneById(id);
        category.setName(categoryRequest.getName());
        category.setDescription(categoryRequest.getDescription());
        category.setType(categoryRequest.getType());
        categoryRepository.update(category);
    }

    @Override
    public void delete(String id) {
        if (getOneById(id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found");
        }
        categoryRepository.deleteById(id);
    }

    @Override
    public Page<Category> getAll(Integer page, Integer size) {
        return categoryRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Category getOneById(String id) {
        return categoryRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Category with id %s not found", id)));
    }
}
