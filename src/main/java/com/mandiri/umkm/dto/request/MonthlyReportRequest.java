package com.mandiri.umkm.dto.request;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MonthlyReportRequest {
    private String userId;
    private int month;
    private int year;
    private BigDecimal totalIncome;
    private BigDecimal totalExpense;
    private BigDecimal endingBalance;
}
