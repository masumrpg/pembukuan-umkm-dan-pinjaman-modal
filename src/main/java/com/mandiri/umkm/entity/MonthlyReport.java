package com.mandiri.umkm.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "monthly_reports")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyReport {

    @Id
    @Column(nullable = false, updatable = false, unique = true)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private int month;

    @Column(nullable = false)
    private int year;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal totalIncome;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal totalExpense;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal endingBalance;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    public enum Status {
        DRAFT, FINAL
    }

    public void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = createdAt;
    }

    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}