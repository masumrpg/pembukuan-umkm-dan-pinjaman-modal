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
        newCategory.onCreate();
        categoryRepository.createNativeQuery(newCategory);
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
        category.onUpdate();
        categoryRepository.updateByIdNativeQuery(category);
    }

    @Override
    public void delete(String id) {
        getOneById(id);
        categoryRepository.deleteNativeQuery(id);
    }

    @Override
    public Page<CategoryResponse> getAll(Integer page, Integer size) {
        Page<Category> categoryPage = categoryRepository.findAllNativeQuery(PageRequest.of(page, size));
        return categoryPage.map(MapperUtils::toCategoryResponse);
    }

    @Override
    public Category getOneById(String id) {
        return categoryRepository.findByIdNativeQuery(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Category with id %s not found", id)));
    }
}
