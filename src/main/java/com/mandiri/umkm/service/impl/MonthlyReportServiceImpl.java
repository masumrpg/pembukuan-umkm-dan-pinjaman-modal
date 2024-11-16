package com.mandiri.umkm.service.impl;

import com.mandiri.umkm.entity.MonthlyReport;
import com.mandiri.umkm.repository.MonthlyReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MonthlyReportServiceImpl {

    private final MonthlyReportRepository monthlyReportRepository;

    public Optional<MonthlyReport> getReportByUserIdAndMonthAndYear(String userId, int month, int year) {
        return monthlyReportRepository.findByUserIdAndMonthAndYear(userId, month, year);
    }

    public List<MonthlyReport> getReportsByUserIdAndYear(String userId, int year) {
        return monthlyReportRepository.findByUserIdAndYear(userId, year);
    }

    public MonthlyReport saveMonthlyReport(MonthlyReport report) {
        return monthlyReportRepository.save(report);
    }
}
