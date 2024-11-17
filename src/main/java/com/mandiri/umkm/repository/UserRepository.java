package com.mandiri.umkm.repository;

import com.mandiri.umkm.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    // Create
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO users " +
            "(id, username, password, full_name, email, phone_number, address, created_at, updated_at, status) " +
            "VALUES (:#{#user.id}, :#{#user.username}, :#{#user.password}, :#{#user.fullName}, :#{#user.email}, " +
            ":#{#user.phoneNumber}, :#{#user.address}, :#{#user.createdAt}, :#{#user.updatedAt}, :#{#user.status.name()})",
            nativeQuery = true)
    void createQueryNative(@Param("user") User user);

    // Read - Get by Id
    @Query(value = "SELECT * FROM users WHERE id = :id", nativeQuery = true)
    Optional<User> findByIdQueryNative(@Param("id") String id);

    // Update
    @Modifying
    @Transactional
    @Query(value = "UPDATE users SET " +
            "username = :#{#user.username}, " +
            "password = :#{#user.password}, " +
            "full_name = :#{#user.fullName}, " +
            "email = :#{#user.email}, " +
            "phone_number = :#{#user.phoneNumber}, " +
            "address = :#{#user.address}, " +
            "updated_at = :#{#user.updatedAt}, " +
            "status = CAST(:#{#user.status.name()} AS VARCHAR) " +
            "WHERE id = :#{#user.id}",
            nativeQuery = true)
    void updateByIdQueryNative(@Param("user") User user);

    // Delete
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM users WHERE id = :id", nativeQuery = true)
    void deleteByIdQueryNative(@Param("id") String id);

    // Additional Queries

    // Find by Username
    @Query(value = "SELECT * FROM users WHERE username = :username", nativeQuery = true)
    Optional<User> findByUsername(@Param("username") String username);

    // Find by Email
    @Query(value = "SELECT * FROM users WHERE email = :email", nativeQuery = true)
    Optional<User> findByEmail(@Param("email") String email);

    // Search with Keyword
    @Query(value = "SELECT * FROM users " +
            "WHERE LOWER(username) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "OR LOWER(full_name) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
            "ORDER BY created_at DESC",
            countQuery = "SELECT count(*) FROM users " +
                    "WHERE LOWER(username) LIKE LOWER(CONCAT('%', :keyword, '%')) " +
                    "OR LOWER(full_name) LIKE LOWER(CONCAT('%', :keyword, '%'))",
            nativeQuery = true)
    Page<User> searchUsers(@Param("keyword") String keyword, Pageable pageable);
}
