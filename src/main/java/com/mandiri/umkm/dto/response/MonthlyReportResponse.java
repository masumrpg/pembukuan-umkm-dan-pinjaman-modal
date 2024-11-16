package com.mandiri.umkm.dto.response;

import com.mandiri.umkm.entity.MonthlyReport;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MonthlyReportResponse {
    private String id;
    private UserResponse user;
    private int month;
    private int year;
    private BigDecimal totalIncome;
    private BigDecimal totalExpense;
    private BigDecimal endingBalance;
    private String createdAt;
    private String updatedAt;
    private MonthlyReport.Status status;
}
