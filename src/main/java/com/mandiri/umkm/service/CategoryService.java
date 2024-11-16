package com.mandiri.umkm.service;

import com.mandiri.umkm.dto.request.CategoryRequest;
import com.mandiri.umkm.dto.response.CategoryResponse;
import com.mandiri.umkm.entity.Category;
import org.springframework.data.domain.Page;

public interface CategoryService {
    void create(CategoryRequest categoryRequest);
    CategoryResponse getById(String id);
    void update(String id, CategoryRequest categoryRequest);
    void delete(String id);
    Page<Category> getAll(Integer page, Integer size);
    Category getOneById(String id);
}
