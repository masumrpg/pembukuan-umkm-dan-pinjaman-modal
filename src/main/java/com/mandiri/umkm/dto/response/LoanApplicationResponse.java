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
    private String userId;
    private BigDecimal loanAmount;
    private int durationMonths;
    private BigDecimal interestRate;
    private LoanApplication.ApprovalStatus approvalStatus;
    private String approvedBy;
    private String approvedAt;
    private String notes;
}
