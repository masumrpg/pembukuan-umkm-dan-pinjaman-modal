package com.mandiri.umkm.service;

import com.mandiri.umkm.dto.request.LoanApplicationRequest;
import com.mandiri.umkm.dto.response.LoanApplicationResponse;
import com.mandiri.umkm.entity.LoanApplication;
import org.springframework.data.domain.Page;

public interface LoanApplicationService {
    void create(LoanApplicationRequest request);

    LoanApplicationResponse getById(String id);

    void delete(String id);

    Page<LoanApplicationResponse> getAll(int page, int size);

    LoanApplication getOneById(String id);

}
