package com.mandiri.umkm.dto.request;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoanApplicationRequest {
    private String userId;
    private BigDecimal loanAmount;
    private int durationMonths;
    private BigDecimal interestRate;
}
