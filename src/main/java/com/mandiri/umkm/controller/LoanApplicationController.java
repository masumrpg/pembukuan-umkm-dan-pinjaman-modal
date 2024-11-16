package com.mandiri.umkm.controller;

import com.mandiri.umkm.entity.LoanApplication;
import com.mandiri.umkm.service.impl.LoanApplicationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
@RequiredArgsConstructor
public class LoanApplicationController {

    private final LoanApplicationServiceImpl loanApplicationServiceImpl;

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getLoanApplicationsByUser(@PathVariable String userId) {
        List<LoanApplication> loans = loanApplicationServiceImpl.getLoanApplicationsByUserId(userId);
        return ResponseEntity.ok(loans);
    }

    @PostMapping
    public ResponseEntity<?> createLoanApplication(@RequestBody LoanApplication loanApplication) {
        LoanApplication createdLoan = loanApplicationServiceImpl.saveLoanApplication(loanApplication);
        return ResponseEntity.ok(createdLoan);
    }
}
