package com.mandiri.umkm.service.impl;

import com.mandiri.umkm.dto.request.TransactionRequest;
import com.mandiri.umkm.dto.request.TransactionUpdateRequest;
import com.mandiri.umkm.dto.response.TransactionResponse;
import com.mandiri.umkm.entity.Category;
import com.mandiri.umkm.entity.Transaction;
import com.mandiri.umkm.entity.User;
import com.mandiri.umkm.repository.TransactionRepository;
import com.mandiri.umkm.service.CategoryService;
import com.mandiri.umkm.service.TransactionService;
import com.mandiri.umkm.service.UserService;
import com.mandiri.umkm.utils.MapperUtils;
import com.mandiri.umkm.utils.StringToLocalDateUtil;
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
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserService userService;
    private final CategoryService categoryService;

    @Override
    public void create(TransactionRequest request) {
        User user = userService.getOneById(request.getUserId());
        Category category = categoryService.getOneById(request.getCategoryId());
        Transaction transaction = Transaction.builder()
                .id(UUID.randomUUID().toString())
                .user(user)
                .category(category)
                .transactionNumber(request.getTransactionNumber())
                .description(request.getDescription())
                .amount(request.getAmount())
                .transactionDate(StringToLocalDateUtil.convertToLocalDate(request.getTransactionDate()))
                .paymentType(request.getPaymentType())
                .transactionProof(request.getTransactionProof())
                .status(request.getStatus())
                .build();
        transaction.onCreate();
        transactionRepository.createNativeQuery(transaction);
    }

    @Override
    public TransactionResponse getById(String id) {
        return MapperUtils.toTransactionResponse(getOneById(id));
    }

    @Override
    public void update(String id, TransactionUpdateRequest request) {
        Category category = categoryService.getOneById(request.getCategoryId());
        Transaction transaction = getOneById(id);
        transaction.setCategory(category);
        transaction.setTransactionNumber(request.getTransactionNumber());
        transaction.setDescription(request.getDescription());
        transaction.setAmount(request.getAmount());
        transaction.setTransactionDate(StringToLocalDateUtil.convertToLocalDate(request.getTransactionDate()));
        transaction.setPaymentType(request.getPaymentType());
        transaction.setStatus(request.getStatus());
        transaction.onUpdate();
        transactionRepository.updateNative(transaction);
    }

    @Override
    public void delete(String id) {
        getOneById(id);
        transactionRepository.deleteByIdNativeQuery(id);
    }

    @Override
    public Page<TransactionResponse> getAll(int page, int size) {
        Page<Transaction> transactionPage = transactionRepository.findAllNativeQuery(PageRequest.of(page, size));
        return transactionPage.map(MapperUtils::toTransactionResponse);
    }

    @Override
    public Transaction getOneById(String id) {
        return transactionRepository.findByIdNativeQuery(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Transaction not found"));
    }
}
