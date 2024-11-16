package com.mandiri.umkm.repository;

import com.mandiri.umkm.entity.LoanApplication;
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
public interface LoanApplicationRepository extends JpaRepository<LoanApplication, String> {
    // Create
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO loan_applications " +
            "(id, user_id, loan_amount, duration_months, interest_rate, application_date, " +
            "approval_status, approved_by, approved_at, notes) " +
            "VALUES (:#{#loan.id}, :#{#loan.user.id}, :#{#loan.loanAmount}, :#{#loan.durationMonths}, " +
            ":#{#loan.interestRate}, :#{#loan.applicationDate}, " +
            "CAST(:#{#loan.approvalStatus.name()} AS VARCHAR), :#{#loan.approvedBy?.id}, " +
            ":#{#loan.approvedAt}, :#{#loan.notes})", nativeQuery = true)
    void create(@Param("loan") LoanApplication loan);

    // Read
    @Query(value = "SELECT la.*, " +
            "u.name as user_name, " +
            "ab.name as approved_by_name " +
            "FROM loan_applications la " +
            "LEFT JOIN users u ON la.user_id = u.id " +
            "LEFT JOIN users ab ON la.approved_by = ab.id " +
            "ORDER BY la.application_date DESC",
            countQuery = "SELECT count(*) FROM loan_applications",
            nativeQuery = true)
    Page<LoanApplication> findAll(Pageable pageable);

    // Read
    @Query(value = "SELECT la.*, " +
            "u.name as user_name, " +
            "ab.name as approved_by_name " +
            "FROM loan_applications la " +
            "LEFT JOIN users u ON la.user_id = u.id " +
            "LEFT JOIN users ab ON la.approved_by = ab.id " +
            "WHERE la.id = :id",
            nativeQuery = true)
    Optional<LoanApplication> findById(@Param("id") String id);

    // Update
    @Modifying
    @Transactional
    @Query(value = "UPDATE loan_applications SET " +
            "loan_amount = :#{#loan.loanAmount}, " +
            "duration_months = :#{#loan.durationMonths}, " +
            "interest_rate = :#{#loan.interestRate}, " +
            "approval_status = CAST(:#{#loan.approvalStatus.name()} AS VARCHAR), " +
            "approved_by = :#{#loan.approvedBy?.id}, " +
            "approved_at = :#{#loan.approvedAt}, " +
            "notes = :#{#loan.notes} " +
            "WHERE id = :#{#loan.id}", nativeQuery = true)
    void update(@Param("loan") LoanApplication loan);

    // Delete
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM loan_applications WHERE id = :id", nativeQuery = true)
    void deleteById(@Param("id") String id);

    // Additional

    // Find by Status
    @Query(value = "SELECT la.*, " +
            "u.name as user_name, " +
            "ab.name as approved_by_name " +
            "FROM loan_applications la " +
            "LEFT JOIN users u ON la.user_id = u.id " +
            "LEFT JOIN users ab ON la.approved_by = ab.id " +
            "WHERE la.approval_status = CAST(:status AS VARCHAR) " +
            "ORDER BY la.application_date DESC",
            countQuery = "SELECT count(*) FROM loan_applications WHERE approval_status = CAST(:status AS VARCHAR)",
            nativeQuery = true)
    Page<LoanApplication> findByStatus(@Param("status") String status, Pageable pageable);

    // Find by User Id
    @Query(value = "SELECT la.*, " +
            "u.name as user_name, " +
            "ab.name as approved_by_name " +
            "FROM loan_applications la " +
            "LEFT JOIN users u ON la.user_id = u.id " +
            "LEFT JOIN users ab ON la.approved_by = ab.id " +
            "WHERE la.user_id = :userId " +
            "ORDER BY la.application_date DESC",
            countQuery = "SELECT count(*) FROM loan_applications WHERE user_id = :userId",
            nativeQuery = true)
    Page<LoanApplication> findByUserId(@Param("userId") String userId, Pageable pageable);
}