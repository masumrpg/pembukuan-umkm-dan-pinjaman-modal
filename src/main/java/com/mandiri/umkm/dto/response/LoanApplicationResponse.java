package com.mandiri.umkm.dto.response;

import com.mandiri.umkm.entity.LoanApplication;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanApplicationResponse {
    private String id;
    private UserResponse user;
    private BigDecimal loanAmount;
    private int durationMonths;
    private BigDecimal interestRate;
    private String applicationDate;
    private LoanApplication.ApprovalStatus approvalStatus;
    private UserResponse approvedBy;
    private String approvedAt;
    private String notes;
}
