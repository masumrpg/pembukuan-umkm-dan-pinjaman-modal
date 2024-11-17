package com.mandiri.umkm.controller;

import com.mandiri.umkm.dto.request.TransactionRequest;
import com.mandiri.umkm.dto.request.TransactionUpdateRequest;
import com.mandiri.umkm.dto.response.CommonResponse;
import com.mandiri.umkm.dto.response.CommonWithPagingResponse;
import com.mandiri.umkm.dto.response.TransactionResponse;
import com.mandiri.umkm.service.TransactionService;
import com.mandiri.umkm.utils.ResponseUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
@Tag(name = "Transaction", description = "APIs for managing transactions")
public class TransactionController {

    private final TransactionService transactionService;

    private static class CommonResponseTransactionResponse extends CommonResponse<TransactionResponse> {
    }

    private static class CommonResponseTransactionListResponse extends CommonWithPagingResponse<List<TransactionResponse>> {
    }

    @Operation(summary = "Add Transaction", description = "Create a new transaction",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Successfully added transaction", content = @Content(schema = @Schema(implementation = CommonResponseTransactionResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
            })
    @PostMapping
    public ResponseEntity<?> addTransaction(@RequestBody TransactionRequest request) {
        transactionService.create(request);
        return ResponseUtils.buildCommonResponse(HttpStatus.CREATED, "Successfully add transaction", null);
    }

    @Operation(summary = "Get Transaction by ID", description = "Retrieve a transaction by its ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved transaction", content = @Content(schema = @Schema(implementation = CommonResponseTransactionResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Transaction not found", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
            })
    @GetMapping("/{id}")
    public ResponseEntity<?> getTransactionById(@PathVariable String id) {
        TransactionResponse transactionResponse = transactionService.getById(id);
        return ResponseUtils.buildCommonResponse(HttpStatus.OK, "Successfully retrieved transaction", transactionResponse);
    }

    @Operation(summary = "Update Transaction", description = "Update an existing transaction by ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully updated transaction", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
            })
    @PutMapping("/{id}")
    public ResponseEntity<?> updateTransaction(@PathVariable String id, @RequestBody TransactionUpdateRequest request) {
        transactionService.update(id, request);
        return ResponseUtils.buildCommonResponse(HttpStatus.OK, "Successfully updated transaction", null);
    }

    @Operation(summary = "Delete Transaction", description = "Delete a transaction by ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully deleted transaction", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Transaction not found", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
            })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTransaction(@PathVariable String id) {
        transactionService.delete(id);
        return ResponseUtils.buildCommonResponse(HttpStatus.OK, "Successfully deleted transaction", null);
    }

    @Operation(summary = "Get All Transactions", description = "Retrieve all transactions with pagination",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved transactions", content = @Content(schema = @Schema(implementation = CommonResponseTransactionListResponse.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
            })
    @GetMapping
    public ResponseEntity<?> getAllTransactions(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<TransactionResponse> transactionResponsePage = transactionService.getAll((page - 1), size);
        return ResponseUtils.buildResponsePage(HttpStatus.OK, "Successfully retrieved transactions", transactionResponsePage);
    }
}
