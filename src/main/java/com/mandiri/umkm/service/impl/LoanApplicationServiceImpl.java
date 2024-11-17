package com.mandiri.umkm.service.impl;

import com.mandiri.umkm.dto.request.LoanApplicationRequest;
import com.mandiri.umkm.dto.response.LoanApplicationResponse;
import com.mandiri.umkm.entity.LoanApplication;
import com.mandiri.umkm.entity.User;
import com.mandiri.umkm.repository.LoanApplicationRepository;
import com.mandiri.umkm.service.LoanApplicationService;
import com.mandiri.umkm.service.UserService;
import com.mandiri.umkm.utils.MapperUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class LoanApplicationServiceImpl implements LoanApplicationService {

    private final LoanApplicationRepository loanApplicationRepository;
    private final UserService userService;

    @Override
    public void create(LoanApplicationRequest request) {
        User user = userService.getOneById(request.getUserId());
        LoanApplication loanApplication = LoanApplication.builder()
                .id(UUID.randomUUID().toString())
                .user(user)
                .loanAmount(request.getLoanAmount())
                .durationMonths(request.getDurationMonths())
                .interestRate(request.getInterestRate())
                .createdAt(LocalDateTime.now())
                .approvalStatus(LoanApplication.ApprovalStatus.PENDING)
                .approvedBy(null)
                .notes("Default note")
                .build();
        loanApplicationRepository.createNativeQuery(loanApplication);
    }

    @Override
    public LoanApplicationResponse getById(String id) {
        return MapperUtils.toLoanApplicationResponse(getOneById(id));
    }

    @Override
    public void delete(String id) {
        getOneById(id);
        loanApplicationRepository.deleteByIdNativeQuery(id);
    }

    @Override
    public Page<LoanApplicationResponse> getAll(int page, int size) {
        Page<LoanApplication> applicationPage = loanApplicationRepository.findAllNativeQuery(PageRequest.of(page, size));
        applicationPage.stream().forEach(loanApplication -> {
            User user = userService.getOneById(loanApplication.getUser().getId());
            loanApplication.setUser(user);
        });
        return applicationPage.map(MapperUtils::toLoanApplicationResponse);
    }

    @Override
    public LoanApplication getOneById(String id) {
        return loanApplicationRepository.findByIdNativeQuery(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "LoanApplication not found"));
    }
}
