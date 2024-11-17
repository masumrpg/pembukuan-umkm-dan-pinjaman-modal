package com.mandiri.umkm.repository;

import com.mandiri.umkm.entity.MonthlyReport;
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
public interface MonthlyReportRepository extends JpaRepository<MonthlyReport, String> {

    // Create
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO monthly_reports (id, user_id, month, year, total_income, total_expense, ending_balance, created_at, updated_at, status) " +
            "VALUES (:#{#report.id}, :#{#report.user.id}, :#{#report.month}, :#{#report.year}, :#{#report.totalIncome}, :#{#report.totalExpense}, " +
            ":#{#report.endingBalance}, :#{#report.createdAt}, :#{#report.updatedAt}, CAST(:#{#report.status.name()} AS VARCHAR))",
            nativeQuery = true)
    void createQueryNative(@Param("report") MonthlyReport report);

    // Read
    @Query(value = "SELECT * FROM monthly_reports WHERE id = :id", nativeQuery = true)
    Optional<MonthlyReport> findByIdQueryNative(@Param("id") String id);

    // Update
    @Modifying
    @Transactional
    @Query(value = "UPDATE monthly_reports SET " +
            "month = :#{#report.month}, " +
            "year = :#{#report.year}, " +
            "total_income = :#{#report.totalIncome}, " +
            "total_expense = :#{#report.totalExpense}, " +
            "ending_balance = :#{#report.endingBalance}, " +
            "updated_at = :#{#report.updatedAt}, " +
            "status = CAST(:#{#report.status.name()} AS VARCHAR) " +
            "WHERE id = :#{#report.id}",
            nativeQuery = true)
    void updateByIdQueryNative(@Param("report") MonthlyReport report);

    // Delete
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM monthly_reports WHERE id = :id", nativeQuery = true)
    void deleteNativeQuery(@Param("id") String id);

    // Read - Get All with Pagination
    @Query(value = "SELECT * FROM monthly_reports ORDER BY year DESC, month DESC",
            countQuery = "SELECT count(*) FROM monthly_reports",
            nativeQuery = true)
    Page<MonthlyReport> findAllQueryNative(Pageable pageable);
}