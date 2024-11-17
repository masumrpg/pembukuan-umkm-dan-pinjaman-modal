package com.mandiri.umkm.dto.request;

import com.mandiri.umkm.entity.Transaction;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionUpdateRequest {
    private String categoryId;
    private String transactionNumber;
    private String description;
    private BigDecimal amount;
    private String transactionDate;
    private Transaction.PaymentType paymentType;
    private Transaction.Status status;
}
