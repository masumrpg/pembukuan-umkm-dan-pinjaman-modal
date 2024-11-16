package com.mandiri.umkm.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.mandiri.umkm.entity.Transaction;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, String> {

    @Query(value = "SELECT * FROM transactions WHERE user_id = :userId", nativeQuery = true)
    List<Transaction> findByUserId(@Param("userId") String userId);

    @Query(value = "SELECT * FROM transactions WHERE category_id = :categoryId", nativeQuery = true)
    List<Transaction> findByCategoryId(@Param("categoryId") String categoryId);

    @Query(value = "SELECT * FROM transactions WHERE transaction_date BETWEEN :startDate AND :endDate", nativeQuery = true)
    List<Transaction> findByTransactionDateBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query(value = "SELECT SUM(amount) FROM transactions WHERE user_id = :userId AND transaction_date BETWEEN :startDate AND :endDate AND category_id IN (SELECT id FROM categories WHERE type = 'INCOME')", nativeQuery = true)
    Double calculateTotalIncome(@Param("userId") String userId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query(value = "SELECT SUM(amount) FROM transactions WHERE user_id = :userId AND transaction_date BETWEEN :startDate AND :endDate AND category_id IN (SELECT id FROM categories WHERE type = 'EXPENSE')", nativeQuery = true)
    Double calculateTotalExpense(@Param("userId") String userId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
