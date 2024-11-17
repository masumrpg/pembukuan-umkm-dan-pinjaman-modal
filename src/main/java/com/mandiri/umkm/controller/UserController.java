package com.mandiri.umkm.controller;

import com.mandiri.umkm.dto.request.UserRequest;
import com.mandiri.umkm.dto.request.UserUpdateRequest;
import com.mandiri.umkm.dto.response.UserResponse;
import com.mandiri.umkm.service.UserService;
import com.mandiri.umkm.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserRequest request) {
        userService.create(request);
        return ResponseUtils.buildCommonResponse(HttpStatus.CREATED, "User successfully created", null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id) {
        UserResponse userResponse = userService.getById(id);
        return ResponseUtils.buildCommonResponse(HttpStatus.OK, "Successfully get user", userResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(
            @PathVariable String id,
            @RequestBody UserUpdateRequest request) {
        userService.update(id, request);
        return ResponseUtils.buildCommonResponse(HttpStatus.OK, "Successfully updated", null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        userService.deleteById(id);
        return ResponseUtils.buildCommonResponse(HttpStatus.OK, "Successfully deleted", null);
    }

    @GetMapping
    public ResponseEntity<?> getAllUser(
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<UserResponse> users = userService.getAll(keyword, (page - 1), size);
        return ResponseUtils.buildResponsePage(HttpStatus.OK, "Successfully get all users", users);
    }
}
