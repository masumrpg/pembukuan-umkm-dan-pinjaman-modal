package com.mandiri.umkm.repository;

import com.mandiri.umkm.entity.MonthlyReport;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MonthlyReportRepository extends JpaRepository<MonthlyReport, String> {

    @Query(value = "SELECT * FROM monthly_reports WHERE user_id = :userId AND month = :month AND year = :year", nativeQuery = true)
    Optional<MonthlyReport> findByUserIdAndMonthAndYear(@Param("userId") String userId, @Param("month") int month, @Param("year") int year);

    @Query(value = "SELECT * FROM monthly_reports WHERE user_id = :userId AND year = :year", nativeQuery = true)
    List<MonthlyReport> findByUserIdAndYear(@Param("userId") String userId, @Param("year") int year);
}