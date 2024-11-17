package com.mandiri.umkm.controller;

import com.mandiri.umkm.dto.request.TransactionRequest;
import com.mandiri.umkm.dto.request.TransactionUpdateRequest;
import com.mandiri.umkm.dto.response.TransactionResponse;
import com.mandiri.umkm.service.TransactionService;
import com.mandiri.umkm.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping
    public ResponseEntity<?> addTransaction(@RequestBody TransactionRequest request) {
        transactionService.create(request);
        return ResponseUtils.buildCommonResponse(HttpStatus.CREATED, "Successfully add transaction", null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTransactionById(@PathVariable String id) {
        TransactionResponse transactionResponse = transactionService.getById(id);
        return ResponseUtils.buildCommonResponse(HttpStatus.OK, "Successfully retrieved transaction", transactionResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTransaction(@PathVariable String id, @RequestBody TransactionUpdateRequest request) {
        transactionService.update(id, request);
        return ResponseUtils.buildCommonResponse(HttpStatus.OK, "Successfully updated transaction", null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTransaction(@PathVariable String id) {
        transactionService.delete(id);
        return ResponseUtils.buildCommonResponse(HttpStatus.OK, "Successfully deleted transaction", null);
    }

    @GetMapping
    public ResponseEntity<?> getAllTransactions(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<TransactionResponse> transactionResponsePage = transactionService.getAll((page - 1), size);
        return ResponseUtils.buildResponsePage(HttpStatus.OK, "Successfully retrieved transactions", transactionResponsePage);
    }
}
