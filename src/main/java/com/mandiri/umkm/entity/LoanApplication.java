package com.mandiri.umkm.entity;

import lombok.*;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "loan_applications")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoanApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal loanAmount;

    @Column(nullable = false)
    private int durationMonths;

    @Column(nullable = false, precision = 5, scale = 2)
    private BigDecimal interestRate;

    @Column(nullable = false, updatable = false)
    private LocalDateTime applicationDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ApprovalStatus approvalStatus;

    public enum ApprovalStatus {
        PENDING, APPROVED, REJECTED
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "approved_by")
    private User approvedBy;

    @Column
    private LocalDateTime approvedAt;

    @Column(columnDefinition = "TEXT")
    private String notes;
}