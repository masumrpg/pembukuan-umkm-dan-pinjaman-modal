package com.mandiri.umkm.repository;

import com.mandiri.umkm.entity.Transaction;
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
public interface TransactionRepository extends JpaRepository<Transaction, String> {
    // Create
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO transactions (id, user_id, category_id, transaction_number, description, amount, transaction_date, payment_type, transaction_proof, created_at, updated_at, status) " +
            "VALUES (:#{#transaction.id}, :#{#transaction.user.id}, :#{#transaction.category.id}, :#{#transaction.transactionNumber}, :#{#transaction.description}, :#{#transaction.amount}, " +
            ":#{#transaction.transactionDate}, CAST(:#{#transaction.paymentType.name()} AS VARCHAR), :#{#transaction.transactionProof}, :#{#transaction.createdAt}, :#{#transaction.updatedAt}, CAST(:#{#transaction.status.name()} AS VARCHAR))",
            nativeQuery = true)
    void createNativeQuery(@Param("transaction") Transaction transaction);

    // Get by id
    @Query(value = "SELECT t.*, " +
            "u.username AS user_name, " +
            "c.name AS category_name " +
            "FROM transactions t " +
            "LEFT JOIN users u ON t.user_id = u.id " +
            "LEFT JOIN categories c ON t.category_id = c.id " +
            "WHERE t.id = :id",
            nativeQuery = true)
    Optional<Transaction> findByIdNativeQuery(@Param("id") String id);

    // Update
    @Modifying
    @Transactional
    @Query(value = "UPDATE transactions SET " +
            "user_id = :#{#transaction.user.id}, " +
            "category_id = :#{#transaction.category.id}, " +
            "transaction_number = :#{#transaction.transactionNumber}, " +
            "description = :#{#transaction.description}, " +
            "amount = :#{#transaction.amount}, " +
            "transaction_date = :#{#transaction.transactionDate}, " +
            "payment_type = CAST(:#{#transaction.paymentType.name()} AS VARCHAR), " +
            "transaction_proof = :#{#transaction.transactionProof}, " +
            "updated_at = :#{#transaction.updatedAt}, " +
            "status = CAST(:#{#transaction.status.name()} AS VARCHAR) " +
            "WHERE id = :#{#transaction.id}",
            nativeQuery = true)
    void updateNative(@Param("transaction") Transaction transaction);

    // Delete
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM transactions WHERE id = :id", nativeQuery = true)
    void deleteByIdNativeQuery(@Param("id") String id);

    // Get all
    @Query(value = "SELECT t.*, " +
            "u.username AS user_name, " +
            "c.name AS category_name " +
            "FROM transactions t " +
            "LEFT JOIN users u ON t.user_id = u.id " +
            "LEFT JOIN categories c ON t.category_id = c.id " +
            "ORDER BY t.transaction_date DESC",
            countQuery = "SELECT count(*) FROM transactions",
            nativeQuery = true)
    Page<Transaction> findAllNativeQuery(Pageable pageable);
}
