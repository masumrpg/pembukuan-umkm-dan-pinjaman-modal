package com.mandiri.umkm.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserUpdateRequest {
    private String fullName;
    private String phoneNumber;
    private String address;
}
