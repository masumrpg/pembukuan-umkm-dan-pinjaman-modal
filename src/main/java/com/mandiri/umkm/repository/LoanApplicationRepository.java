package com.mandiri.umkm.repository;

import com.mandiri.umkm.entity.LoanApplication;
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
public interface LoanApplicationRepository extends JpaRepository<LoanApplication, String> {
    // Create
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO loan_applications " +
            "(id, user_id, loan_amount, duration_months, interest_rate, " +
            "approval_status, created_at, approved_by, approved_at, notes) " +
            "VALUES (:#{#loan.id}, :#{#loan.user.id}, :#{#loan.loanAmount}, :#{#loan.durationMonths}, " +
            ":#{#loan.interestRate}, " +
            "CAST(:#{#loan.approvalStatus.name()} AS VARCHAR), :#{#loan.createdAt}, :#{#loan.approvedBy?.id}, " +
            ":#{#loan.approvedAt}, :#{#loan.notes})", nativeQuery = true)
    void createNativeQuery(@Param("loan") LoanApplication loan);

    @Query(value = """
            SELECT *
            FROM loan_applications la
            """,
            countQuery = "SELECT count(*) FROM loan_applications",
            nativeQuery = true)
    Page<LoanApplication> findAllNativeQuery(Pageable pageable);

    @Query(value = """
            SELECT *
            FROM loan_applications la
            WHERE la.id = :id
            """,
            nativeQuery = true)
    Optional<LoanApplication> findByIdNativeQuery(@Param("id") String id);

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
    void deleteByIdNativeQuery(@Param("id") String id);
}