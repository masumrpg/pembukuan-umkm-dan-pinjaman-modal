package com.mandiri.umkm.utils;

import com.mandiri.umkm.dto.response.CommonResponse;
import com.mandiri.umkm.dto.response.CommonWithPagingResponse;
import com.mandiri.umkm.dto.response.PagingResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ResponseUtils {
    public static <T> ResponseEntity<CommonResponse<T>> buildCommonResponse(HttpStatus httpStatus, String message, T data) {
        CommonResponse<T> commonResponse = new CommonResponse<>(httpStatus.value(), message, data);
        return ResponseEntity.status(httpStatus).body(commonResponse);
    }

    public static <T> ResponseEntity<CommonWithPagingResponse<?>> buildResponsePage(
            HttpStatus httpStatus,
            String message,
            Page<T> page
    ) {
        PagingResponse pagingResponse = PagingResponse.builder()
                .totalPages(page.getTotalPages())
                .totalItems(page.getTotalElements())
                .page(page.getPageable().getPageNumber() + 1)
                .size(page.getSize())
                .build();

        CommonWithPagingResponse<List<T>> response = new CommonWithPagingResponse<>(
                httpStatus.value(),
                message,
                page.getContent(),
                pagingResponse
        );
        return ResponseEntity.status(httpStatus).body(response);
    }
}