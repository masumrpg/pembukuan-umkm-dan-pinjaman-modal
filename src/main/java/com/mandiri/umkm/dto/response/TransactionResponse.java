package com.mandiri.umkm.dto.response;

import com.mandiri.umkm.entity.Transaction;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionResponse {
    private String id;
    private UserResponse user;
    private CategoryResponse category;
    private String transactionNumber;
    private String description;
    private BigDecimal amount;
    private Transaction.PaymentType paymentType;
    private String transactionProof;
    private String createdAt;
    private String updatedAt;
    private Transaction.Status status;
}
