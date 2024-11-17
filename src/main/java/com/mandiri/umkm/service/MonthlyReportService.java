package com.mandiri.umkm.service;

import com.mandiri.umkm.dto.request.MonthlyReportRequest;
import com.mandiri.umkm.dto.request.MonthlyReportUpdateRequest;
import com.mandiri.umkm.dto.response.MonthlyReportResponse;
import com.mandiri.umkm.entity.MonthlyReport;
import org.springframework.data.domain.Page;

public interface MonthlyReportService {
    void create(MonthlyReportRequest request);

    MonthlyReportResponse getById(String id);

    void update(String id, MonthlyReportUpdateRequest request);

    void delete(String id);

    Page<MonthlyReportResponse> getAll(int page, int size);

    MonthlyReport getOneById(String id);
}
