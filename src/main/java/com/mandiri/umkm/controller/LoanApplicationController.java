package com.mandiri.umkm.controller;

import com.mandiri.umkm.dto.request.LoanApplicationRequest;
import com.mandiri.umkm.dto.response.LoanApplicationResponse;
import com.mandiri.umkm.service.LoanApplicationService;
import com.mandiri.umkm.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/loans")
@RequiredArgsConstructor
public class LoanApplicationController {

    private final LoanApplicationService loanApplicationService;

    @PostMapping
    public ResponseEntity<?> createLoanApplication(@RequestBody LoanApplicationRequest request) {
        loanApplicationService.create(request);
        return ResponseUtils.buildCommonResponse(HttpStatus.CREATED, "Successfully created loan", null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLoanApplicationById(@PathVariable String id) {
        LoanApplicationResponse loanApplicationResponse = loanApplicationService.getById(id);
        return ResponseUtils.buildCommonResponse(HttpStatus.OK, "Successfully retrieved loan application", loanApplicationResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLoanApplicationById(@PathVariable String id) {
        loanApplicationService.delete(id);
        return ResponseUtils.buildCommonResponse(HttpStatus.OK, "Successfully deleted loan application", null);
    }

    @GetMapping
    public ResponseEntity<?> getAllLoanApplications(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<LoanApplicationResponse> loanApplicationResponses = loanApplicationService.getAll((page - 1), size);
        return ResponseUtils.buildResponsePage(HttpStatus.OK, "Successfully retrieved loan applications", loanApplicationResponses);
    }
}
