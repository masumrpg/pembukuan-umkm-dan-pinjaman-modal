package com.mandiri.umkm.service;

import com.mandiri.umkm.dto.request.LoanApplicationRequest;
import com.mandiri.umkm.entity.LoanApplication;

public interface LoanApplicationService {
    void create(LoanApplicationRequest request);
}
