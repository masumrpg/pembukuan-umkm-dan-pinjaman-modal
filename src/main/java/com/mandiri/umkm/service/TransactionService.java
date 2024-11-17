package com.mandiri.umkm.service;

import com.mandiri.umkm.dto.request.TransactionRequest;
import com.mandiri.umkm.dto.request.TransactionUpdateRequest;
import com.mandiri.umkm.dto.response.TransactionResponse;
import com.mandiri.umkm.entity.Transaction;
import org.springframework.data.domain.Page;

public interface TransactionService {
    void create(TransactionRequest request);

    TransactionResponse getById(String id);

    void update(String id, TransactionUpdateRequest request);

    void delete(String id);

    Page<TransactionResponse> getAll(int page, int size);

    Transaction getOneById(String id);
}
