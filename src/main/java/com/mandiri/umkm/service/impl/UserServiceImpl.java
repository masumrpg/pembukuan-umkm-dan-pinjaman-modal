package com.mandiri.umkm.service.impl;

import com.mandiri.umkm.dto.request.UserRequest;
import com.mandiri.umkm.dto.request.UserUpdateRequest;
import com.mandiri.umkm.dto.response.UserResponse;
import com.mandiri.umkm.entity.User;
import com.mandiri.umkm.repository.UserRepository;
import com.mandiri.umkm.service.UserService;
import com.mandiri.umkm.utils.MapperUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.UUID;


@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void create(UserRequest request) {
        validateUserRequest(request);

        User user = User.builder()
                .id(UUID.randomUUID().toString())
                .username(request.getUsername())
                .password(request.getPassword())
                .email(request.getEmail())
                .fullName(request.getFullName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .address(request.getAddress())
                .status(User.Status.ACTIVE)
                .build();
        user.onCreate();
        userRepository.createQueryNative(user);
    }

    @Override
    public UserResponse getById(String id) {
        return MapperUtils.toUserResponse(getOneById(id));
    }

    @Override
    public void update(String id, UserUpdateRequest request) {
        User user = getOneById(id);
        user.setFullName(request.getFullName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setAddress(request.getAddress());
        user.setUpdatedAt(LocalDateTime.now());
        user.onUpdate();
        userRepository.updateByIdQueryNative(user);
    }

    @Override
    public void deleteById(String id) {
        getOneById(id);
        userRepository.deleteByIdQueryNative(id);
    }

    @Override
    public Page<UserResponse> getAll(String keyword, int page, int size) {
        Page<User> userPage = userRepository.searchUsers(keyword, PageRequest.of(page, size));
        return userPage.map(MapperUtils::toUserResponse);
    }

    @Override
    public User getOneById(String id) {
        return userRepository.findByIdQueryNative(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + id));
    }

    private void validateUserRequest(UserRequest request) {
        userRepository.findByUsername(request.getUsername()).ifPresent(user -> {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists");
        });
        userRepository.findByEmail(request.getEmail()).ifPresent(user -> {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        });
    }
}
