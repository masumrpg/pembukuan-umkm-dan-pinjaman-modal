package com.mandiri.umkm.repository;

import com.mandiri.umkm.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Query(value = "SELECT * FROM users WHERE username = :username", nativeQuery = true)
    Optional<User> findByUsername(@Param("username") String username);

    @Query(value = "SELECT * FROM users WHERE email = :email", nativeQuery = true)
    Optional<User> findByEmail(@Param("email") String email);

    @Query(value = "SELECT COUNT(*) > 0 FROM users WHERE username = :username", nativeQuery = true)
    boolean existsByUsername(@Param("username") String username);

    @Query(value = "SELECT COUNT(*) > 0 FROM users WHERE email = :email", nativeQuery = true)
    boolean existsByEmail(@Param("email") String email);
}
