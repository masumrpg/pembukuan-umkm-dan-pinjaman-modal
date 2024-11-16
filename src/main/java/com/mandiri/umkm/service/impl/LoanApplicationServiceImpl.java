package com.mandiri.umkm.service.impl;

import com.mandiri.umkm.dto.request.LoanApplicationRequest;
import com.mandiri.umkm.entity.LoanApplication;
import com.mandiri.umkm.repository.LoanApplicationRepository;
import com.mandiri.umkm.service.LoanApplicationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class LoanApplicationServiceImpl implements LoanApplicationService {

    private final LoanApplicationRepository loanApplicationRepository;

    @Override
    public void create(LoanApplicationRequest request) {
        LoanApplication loanApplication = LoanApplication.builder()
                .id(UUID.randomUUID().toString())
                .user()
                .loanAmount(request.getLoanAmount())
                .durationMonths(request.getDurationMonths())
                .interestRate(request.getInterestRate())
                .applicationDate(LocalDateTime.now())
                .approvalStatus(LoanApplication.ApprovalStatus.PENDING)
                .approvedBy(null)
                .notes("Default note")
                .build();
        loanApplicationRepository.create(loanApplication);
    }

    @Override
    public Page<LoanApplication> findAll(int page, int size) {
        return loanApplicationRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public LoanApplication findById(String id) {
        return loanApplicationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Loan application not found with id: " + id));
    }

    @Override
    public void update(LoanApplication loan) {
        if (!loanApplicationRepository.existsById(loan.getId())) {
            throw new ResourceNotFoundException("Loan application not found with id: " + loan.getId());
        }
        loanApplicationRepository.update(loan);
    }

    @Override
    public void deleteById(String id) {
        if (!loanApplicationRepository.existsById(id)) {
            throw new ResourceNotFoundException("Loan application not found with id: " + id);
        }
        loanApplicationRepository.deleteById(id);
    }

    @Override
    public Page<LoanApplication> findByStatus(LoanApplication.ApprovalStatus status, int page, int size) {
        return loanApplicationRepository.findByStatus(status.name(), PageRequest.of(page, size));
    }

    @Override
    public Page<LoanApplication> findByUserId(String userId, int page, int size) {
        return loanApplicationRepository.findByUserId(userId, PageRequest.of(page, size));
    }
}
