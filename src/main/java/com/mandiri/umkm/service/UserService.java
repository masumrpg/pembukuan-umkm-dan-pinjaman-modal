package com.mandiri.umkm.service;

import com.mandiri.umkm.dto.request.UserRequest;
import com.mandiri.umkm.dto.request.UserUpdateRequest;
import com.mandiri.umkm.dto.response.UserResponse;
import com.mandiri.umkm.entity.User;
import org.springframework.data.domain.Page;

public interface UserService {
    void create(UserRequest request);

    UserResponse getById(String id);

    void update(String id, UserUpdateRequest request);

    void deleteById(String id);

    Page<UserResponse> getAll(String keyword, int page, int size);

    User getOneById(String id);
}
