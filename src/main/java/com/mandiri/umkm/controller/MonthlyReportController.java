package com.mandiri.umkm.controller;

import com.mandiri.umkm.dto.request.MonthlyReportRequest;
import com.mandiri.umkm.dto.request.MonthlyReportUpdateRequest;
import com.mandiri.umkm.dto.response.MonthlyReportResponse;
import com.mandiri.umkm.service.MonthlyReportService;
import com.mandiri.umkm.utils.ResponseUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class MonthlyReportController {

    private final MonthlyReportService monthlyReportService;

    @PostMapping
    public ResponseEntity<?> createMonthlyReport(@RequestBody MonthlyReportRequest request) {
        monthlyReportService.create(request);
        return ResponseUtils.buildCommonResponse(HttpStatus.CREATED, "Report successfully created", null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMonthlyReportById(@PathVariable String id) {
        MonthlyReportResponse monthlyReportResponse = monthlyReportService.getById(id);
        return ResponseUtils.buildCommonResponse(HttpStatus.OK, "Report successfully retrieved", monthlyReportResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMonthlyReport(@PathVariable String id, @RequestBody MonthlyReportUpdateRequest request) {
        monthlyReportService.update(id, request);
        return ResponseUtils.buildCommonResponse(HttpStatus.OK, "Report successfully updated", null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMonthlyReport(@PathVariable String id) {
        monthlyReportService.delete(id);
        return ResponseUtils.buildCommonResponse(HttpStatus.OK, "Report successfully deleted", null);
    }

    @GetMapping
    public ResponseEntity<?> getAllMonthlyReports(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<MonthlyReportResponse> monthlyReportResponses = monthlyReportService.getAll((page - 1), size);
        return ResponseUtils.buildResponsePage(HttpStatus.OK, "Report successfully retrieved", monthlyReportResponses);
    }
}
