package com.mandiri.umkm.utils;

import com.mandiri.umkm.dto.response.*;
import com.mandiri.umkm.entity.*;

public class MapperUtils {
    public static UserResponse toUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .address(user.getAddress())
                .createdAt(user.getCreatedAt() != null ? user.getCreatedAt().toString() : null)
                .updatedAt(user.getUpdatedAt() != null ? user.getUpdatedAt().toString() : null)
                .status(user.getStatus())
                .build();
    }

    public static CategoryResponse toCategoryResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .type(category.getType())
                .createdAt(category.getCreatedAt() != null ? category.getCreatedAt().toString() : null)
                .updatedAt(category.getUpdatedAt() != null ? category.getUpdatedAt().toString() : null)
                .build();
    }

    public static LoanApplicationResponse toLoanApplicationResponse(LoanApplication loanApplication) {
        return LoanApplicationResponse.builder()
                .id(loanApplication.getId())
                .user(toUserResponse(loanApplication.getUser()))
                .loanAmount(loanApplication.getLoanAmount())
                .durationMonths(loanApplication.getDurationMonths())
                .interestRate(loanApplication.getInterestRate())
                .applicationDate(loanApplication.getApplicationDate() != null ? loanApplication.getApplicationDate().toString() : null)
                .approvalStatus(loanApplication.getApprovalStatus())
                .approvedBy(toUserResponse(loanApplication.getApprovedBy()))
                .approvedAt(loanApplication.getApprovedAt() != null ? loanApplication.getApprovedAt().toString() : null)
                .notes(loanApplication.getNotes())
                .build();
    }

    public static MonthlyReportResponse toMonthlyReportResponse(MonthlyReport monthlyReport, User user) {
        return MonthlyReportResponse.builder()
                .id(monthlyReport.getId())
                .user(toUserResponse(user))
                .month(monthlyReport.getMonth())
                .year(monthlyReport.getYear())
                .totalIncome(monthlyReport.getTotalIncome())
                .totalExpense(monthlyReport.getTotalExpense())
                .endingBalance(monthlyReport.getEndingBalance())
                .createdAt(monthlyReport.getCreatedAt() != null ? monthlyReport.getCreatedAt().toString() : null)
                .updatedAt(monthlyReport.getUpdatedAt() != null ? monthlyReport.getUpdatedAt().toString() : null)
                .status(monthlyReport.getStatus())
                .build();
    }

    public static TransactionResponse toTransactionResponse(Transaction transaction) {
        return TransactionResponse.builder()
                .id(transaction.getId())
                .user(toUserResponse(transaction.getUser()))
                .category(toCategoryResponse(transaction.getCategory()))
                .transactionNumber(transaction.getTransactionNumber())
                .description(transaction.getDescription())
                .amount(transaction.getAmount())
                .paymentType(transaction.getPaymentType())
                .transactionProof(transaction.getTransactionProof())
                .createdAt(transaction.getCreatedAt() != null ? transaction.getCreatedAt().toString() : null)
                .updatedAt(transaction.getUpdatedAt() != null ? transaction.getUpdatedAt().toString() : null)
                .status(transaction.getStatus())
                .build();
    }


}
