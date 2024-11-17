package com.mandiri.umkm.service.impl;

import com.mandiri.umkm.dto.request.MonthlyReportRequest;
import com.mandiri.umkm.dto.request.MonthlyReportUpdateRequest;
import com.mandiri.umkm.dto.response.MonthlyReportResponse;
import com.mandiri.umkm.entity.MonthlyReport;
import com.mandiri.umkm.entity.User;
import com.mandiri.umkm.repository.MonthlyReportRepository;
import com.mandiri.umkm.service.MonthlyReportService;
import com.mandiri.umkm.service.UserService;
import com.mandiri.umkm.utils.MapperUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class MonthlyReportServiceImpl implements MonthlyReportService {

    private final MonthlyReportRepository monthlyReportRepository;
    private final UserService userService;

    @Override
    public void create(MonthlyReportRequest request) {
        User user = userService.getOneById(request.getUserId());
        MonthlyReport monthlyReport = MonthlyReport.builder()
                .id(UUID.randomUUID().toString())
                .user(user)
                .month(request.getMonth())
                .year(request.getYear())
                .totalIncome(request.getTotalIncome())
                .totalExpense(request.getTotalExpense())
                .endingBalance(request.getEndingBalance())
                .status(MonthlyReport.Status.DRAFT)
                .build();
        monthlyReport.onCreate();
        monthlyReportRepository.createQueryNative(monthlyReport);
    }

    @Override
    public MonthlyReportResponse getById(String id) {
        return MapperUtils.toMonthlyReportResponse(getOneById(id));
    }

    @Override
    public void update(String id, MonthlyReportUpdateRequest request) {
        MonthlyReport monthlyReport = getOneById(id);
        monthlyReport.setMonth(request.getMonth());
        monthlyReport.setYear(request.getYear());
        monthlyReport.setTotalIncome(request.getTotalIncome());
        monthlyReport.setTotalExpense(request.getTotalExpense());
        monthlyReport.setEndingBalance(request.getEndingBalance());
        monthlyReport.onUpdate();
        monthlyReportRepository.updateByIdQueryNative(monthlyReport);
    }

    @Override
    public void delete(String id) {
        getOneById(id);
        monthlyReportRepository.deleteNativeQuery(id);
    }

    @Override
    public Page<MonthlyReportResponse> getAll(int page, int size) {
        Page<MonthlyReport> monthlyReports = monthlyReportRepository.findAllQueryNative(PageRequest.of(page, size));
        return monthlyReports.map(MapperUtils::toMonthlyReportResponse);
    }

    @Override
    public MonthlyReport getOneById(String id) {
        return monthlyReportRepository.findByIdQueryNative(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Report not found"));
    }
}
