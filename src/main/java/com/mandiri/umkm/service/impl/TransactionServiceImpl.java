package com.mandiri.umkm.service.impl;

import com.mandiri.umkm.entity.Transaction;
import com.mandiri.umkm.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl {

    private final TransactionRepository transactionRepository;

    public List<Transaction> getTransactionsByUserId(String userId) {
        return transactionRepository.findByUserId(userId);
    }

    public List<Transaction> getTransactionsByCategoryId(String categoryId) {
        return transactionRepository.findByCategoryId(categoryId);
    }

    public List<Transaction> getTransactionsByDateRange(LocalDate startDate, LocalDate endDate) {
        return transactionRepository.findByTransactionDateBetween(startDate, endDate);
    }

    public Double calculateTotalIncome(String userId, LocalDate startDate, LocalDate endDate) {
        return transactionRepository.calculateTotalIncome(userId, startDate, endDate);
    }

    public Double calculateTotalExpense(String userId, LocalDate startDate, LocalDate endDate) {
        return transactionRepository.calculateTotalExpense(userId, startDate, endDate);
    }

    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }
}
