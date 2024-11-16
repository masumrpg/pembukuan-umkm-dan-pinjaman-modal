package com.mandiri.umkm.controller;

import com.mandiri.umkm.entity.User;
import com.mandiri.umkm.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
        Optional<User> user = userServiceImpl.findUserByUsername(username);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        if (userServiceImpl.isUsernameExists(user.getUsername())) {
            return ResponseEntity.badRequest().body("Username already exists");
        }
        if (userServiceImpl.isEmailExists(user.getEmail())) {
            return ResponseEntity.badRequest().body("Email already exists");
        }
        User createdUser = userServiceImpl.saveUser(user);
        return ResponseEntity.ok(createdUser);
    }
}
