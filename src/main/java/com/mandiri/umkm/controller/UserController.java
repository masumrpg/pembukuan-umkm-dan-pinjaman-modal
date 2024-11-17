package com.mandiri.umkm.controller;

import com.mandiri.umkm.dto.request.UserRequest;
import com.mandiri.umkm.dto.request.UserUpdateRequest;
import com.mandiri.umkm.dto.response.CommonResponse;
import com.mandiri.umkm.dto.response.CommonWithPagingResponse;
import com.mandiri.umkm.dto.response.UserResponse;
import com.mandiri.umkm.service.UserService;
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
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "User", description = "APIs for managing users")
public class UserController {

    private final UserService userService;

    private static class CommonResponseUserResponse extends CommonResponse<UserResponse> {
    }

    private static class CommonResponseUserListResponse extends CommonWithPagingResponse<List<UserResponse>> {
    }

    @Operation(summary = "Create User", description = "Create a new user",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Successfully created user", content = @Content(schema = @Schema(implementation = CommonResponseUserResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
            })
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserRequest request) {
        userService.create(request);
        return ResponseUtils.buildCommonResponse(HttpStatus.CREATED, "User successfully created", null);
    }

    @Operation(summary = "Get User by ID", description = "Retrieve a user by its ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved user", content = @Content(schema = @Schema(implementation = CommonResponseUserResponse.class))),
                    @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
            })
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable String id) {
        UserResponse userResponse = userService.getById(id);
        return ResponseUtils.buildCommonResponse(HttpStatus.OK, "Successfully get user", userResponse);
    }

    @Operation(summary = "Update User", description = "Update an existing user by ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully updated user", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
            })
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(
            @PathVariable String id,
            @RequestBody UserUpdateRequest request) {
        userService.update(id, request);
        return ResponseUtils.buildCommonResponse(HttpStatus.OK, "Successfully updated", null);
    }

    @Operation(summary = "Delete User", description = "Delete a user by ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully deleted user", content = @Content),
                    @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
            })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        userService.deleteById(id);
        return ResponseUtils.buildCommonResponse(HttpStatus.OK, "Successfully deleted", null);
    }

    @Operation(summary = "Get All Users", description = "Retrieve all users with optional search and pagination",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved users", content = @Content(schema = @Schema(implementation = CommonResponseUserListResponse.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
            })
    @GetMapping
    public ResponseEntity<?> getAllUser(
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<UserResponse> users = userService.getAll(keyword, (page - 1), size);
        return ResponseUtils.buildResponsePage(HttpStatus.OK, "Successfully get all users", users);
    }
}
