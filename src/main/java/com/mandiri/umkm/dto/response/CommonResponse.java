package com.mandiri.umkm.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class CommonResponse<T> {
    private Integer status;
    private String message;
    private T data;
}