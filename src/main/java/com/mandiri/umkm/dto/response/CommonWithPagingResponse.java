package com.mandiri.umkm.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommonWithPagingResponse<T> {
    private Integer status;
    private String message;
    private T data;
    private PagingResponse paging;
}
