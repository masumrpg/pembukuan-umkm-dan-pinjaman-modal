package com.mandiri.umkm.controller;

import com.mandiri.umkm.entity.Transaction;
import com.mandiri.umkm.service.impl.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionServiceImpl transactionServiceImpl;

    @Autowired
    public TransactionController(TransactionServiceImpl transactionServiceImpl) {
        this.transactionServiceImpl = transactionServiceImpl;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getTransactionsByUser(@PathVariable String userId) {
        List<Transaction> transactions = transactionServiceImpl.getTransactionsByUserId(userId);
        return ResponseEntity.ok(transactions);
    }

    @GetMapping("/report")
    public ResponseEntity<?> getTransactionsByDateRange(@RequestParam String userId, @RequestParam String startDate, @RequestParam String endDate) {
        List<Transaction> transactions = transactionServiceImpl.getTransactionsByDateRange(LocalDate.parse(startDate), LocalDate.parse(endDate));
        return ResponseEntity.ok(transactions);
    }

    @PostMapping
    public ResponseEntity<?> createTransaction(@RequestBody Transaction transaction) {
        Transaction createdTransaction = transactionServiceImpl.saveTransaction(transaction);
        return ResponseEntity.ok(createdTransaction);
    }
}
