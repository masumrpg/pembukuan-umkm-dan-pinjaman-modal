package com.mandiri.umkm.repository;

import com.mandiri.umkm.entity.Category;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
    // Insert
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO categories (id, name, description, type, created_at, updated_at) " +
            "VALUES (:#{#category.id}, :#{#category.name}, :#{#category.description}, CAST(:#{#category.type.name()} AS VARCHAR), " +
            "NOW(), NOW())", nativeQuery = true)
    void create(@Param("category") Category category);

    // Read
    @Query(value = "SELECT * FROM categories WHERE id = :id", nativeQuery = true)
    Optional<Category> findById(@Param("id") String id);

    // Update
    @Modifying
    @Transactional
    @Query(value = "UPDATE categories SET " +
            "name = :#{#category.name}, " +
            "description = :#{#category.description}, " +
            "type = CAST(:#{#category.type.name()} AS VARCHAR), " +
            "updated_at = NOW() " +
            "WHERE id = :#{#category.id}", nativeQuery = true)
    void update(@Param("category") Category category);

    // Delete
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM categories WHERE id = :id", nativeQuery = true)
    void deleteById(@Param("id") String id);

    // Get All
    @Query(value = "SELECT * FROM categories ORDER BY created_at DESC",
            countQuery = "SELECT count(*) FROM categories",
            nativeQuery = true)
    Page<Category> findAll(Pageable pageable);
}
