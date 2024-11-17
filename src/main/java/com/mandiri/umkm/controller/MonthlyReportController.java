package com.mandiri.umkm.controller;

import com.mandiri.umkm.dto.request.MonthlyReportRequest;
import com.mandiri.umkm.dto.request.MonthlyReportUpdateRequest;
import com.mandiri.umkm.dto.response.CommonResponse;
import com.mandiri.umkm.dto.response.CommonWithPagingResponse;
import com.mandiri.umkm.dto.response.MonthlyReportResponse;
import com.mandiri.umkm.service.MonthlyReportService;
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
@RequestMapping("/api/reports")
@RequiredArgsConstructor
@Tag(name = "Monthly Report", description = "APIs for managing monthly reports")
public class MonthlyReportController {

    private final MonthlyReportService monthlyReportService;

    private static class CommonResponseMonthlyReportResponse extends CommonResponse<MonthlyReportResponse> {
    }

    private static class CommonResponseMonthlyReportListResponse extends CommonWithPagingResponse<List<MonthlyReportResponse>> {
    }

    @Operation(summary = "Create Monthly Report", description = "Create a new monthly report",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Successfully created monthly report", content = @Content(schema = @Schema(implementation = CommonResponseMonthlyReportResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
            })
    @PostMapping
    public ResponseEntity<?> createMonthlyReport(@RequestBody MonthlyReportRequest request) {
        monthlyReportService.create(request);
        return ResponseUtils.buildCommonResponse(HttpStatus.CREATED, "Report successfully created", null);
    }

    @Operation(summary = "Get Monthly Report by ID", description = "Retrieve a monthly report by its ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved monthly report", content = @Content(schema = @Schema(implementation = CommonResponseMonthlyReportResponse.class))),
                    @ApiResponse(responseCode = "404", description = "Monthly report not found", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
            })
    @GetMapping("/{id}")
    public ResponseEntity<?> getMonthlyReportById(@PathVariable String id) {
        MonthlyReportResponse monthlyReportResponse = monthlyReportService.getById(id);
        return ResponseUtils.buildCommonResponse(HttpStatus.OK, "Report successfully retrieved", monthlyReportResponse);
    }

    @Operation(summary = "Update Monthly Report", description = "Update an existing monthly report by ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully updated monthly report", content = @Content),
                    @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
            })
    @PutMapping("/{id}")
    public ResponseEntity<?> updateMonthlyReport(@PathVariable String id, @RequestBody MonthlyReportUpdateRequest request) {
        monthlyReportService.update(id, request);
        return ResponseUtils.buildCommonResponse(HttpStatus.OK, "Report successfully updated", null);
    }

    @Operation(summary = "Delete Monthly Report", description = "Delete a monthly report by ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully deleted monthly report", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Monthly report not found", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
            })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMonthlyReport(@PathVariable String id) {
        monthlyReportService.delete(id);
        return ResponseUtils.buildCommonResponse(HttpStatus.OK, "Report successfully deleted", null);
    }

    @Operation(summary = "Get All Monthly Reports", description = "Retrieve all monthly reports with pagination",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully retrieved monthly reports", content = @Content(schema = @Schema(implementation = CommonResponseMonthlyReportListResponse.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
            })
    @GetMapping
    public ResponseEntity<?> getAllMonthlyReports(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<MonthlyReportResponse> monthlyReportResponses = monthlyReportService.getAll((page - 1), size);
        return ResponseUtils.buildResponsePage(HttpStatus.OK, "Report successfully retrieved", monthlyReportResponses);
    }
}
