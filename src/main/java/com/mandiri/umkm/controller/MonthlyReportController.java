package com.mandiri.umkm.controller;

import com.mandiri.umkm.entity.MonthlyReport;
import com.mandiri.umkm.service.impl.MonthlyReportServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/reports")
public class MonthlyReportController {

    private final MonthlyReportServiceImpl monthlyReportServiceImpl;

    @Autowired
    public MonthlyReportController(MonthlyReportServiceImpl monthlyReportServiceImpl) {
        this.monthlyReportServiceImpl = monthlyReportServiceImpl;
    }

    @GetMapping("/{userId}/{month}/{year}")
    public ResponseEntity<?> getMonthlyReport(@PathVariable String userId, @PathVariable int month, @PathVariable int year) {
        Optional<MonthlyReport> report = monthlyReportServiceImpl.getReportByUserIdAndMonthAndYear(userId, month, year);
        return report.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createMonthlyReport(@RequestBody MonthlyReport report) {
        MonthlyReport createdReport = monthlyReportServiceImpl.saveMonthlyReport(report);
        return ResponseEntity.ok(createdReport);
    }
}
