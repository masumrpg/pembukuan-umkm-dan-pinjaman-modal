package com.mandiri.umkm.controller;

import com.mandiri.umkm.dto.request.LoanApplicationRequest;
import com.mandiri.umkm.dto.response.CommonResponse;
import com.mandiri.umkm.dto.response.CommonWithPagingResponse;
import com.mandiri.umkm.dto.response.LoanApplicationResponse;
import com.mandiri.umkm.service.LoanApplicationService;
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
@RequestMapping("/api/loans")
@RequiredArgsConstructor
@Tag(name = "Loan Application", description = "APIs for managing loan applications")
public class LoanApplicationController {

    private final LoanApplicationService loanApplicationService;

    private static class CommonResponseLoanApplicationResponse extends CommonResponse<LoanApplicationResponse> {
    }

    private static class CommonResponseLoanApplicationListResponse extends CommonWithPagingResponse<List<LoanApplicationResponse>> {
    }

    @Operation(summary = "Create Loan Application", description = "Create a new loan application",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Successfully created loan application", content = @Content(schema = @Schema(implementation = CommonResponseLoanApplicationResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
            })
    @PostMapping
    public ResponseEntity<?> createLoanApplication(@RequestBody LoanApplicationRequest request) {
        loanApplicationService.create(request);
        return ResponseUtils.buildCommonResponse(HttpStatus.CREATED, "Successfully created loan", null);
    }

    @Operation(summary = "Get Loan Application by ID", description = "Retrieve a loan application by its ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved loan application", content = @Content(schema = @Schema(implementation = CommonResponseLoanApplicationResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Loan application not found", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
            })
    @GetMapping("/{id}")
    public ResponseEntity<?> getLoanApplicationById(@PathVariable String id) {
        LoanApplicationResponse loanApplicationResponse = loanApplicationService.getById(id);
        return ResponseUtils.buildCommonResponse(HttpStatus.OK, "Successfully retrieved loan application", loanApplicationResponse);
    }

    @Operation(summary = "Delete Loan Application", description = "Delete a loan application by ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully deleted loan application", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Loan application not found", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
            })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLoanApplicationById(@PathVariable String id) {
        loanApplicationService.delete(id);
        return ResponseUtils.buildCommonResponse(HttpStatus.OK, "Successfully deleted loan application", null);
    }

    @Operation(summary = "Get All Loan Applications", description = "Retrieve all loan applications with pagination",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved loan applications", content = @Content(schema = @Schema(implementation = CommonResponseLoanApplicationListResponse.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
            })
    @GetMapping
    public ResponseEntity<?> getAllLoanApplications(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<LoanApplicationResponse> loanApplicationResponses = loanApplicationService.getAll((page - 1), size);
        return ResponseUtils.buildResponsePage(HttpStatus.OK, "Successfully retrieved loan applications", loanApplicationResponses);
    }
}
