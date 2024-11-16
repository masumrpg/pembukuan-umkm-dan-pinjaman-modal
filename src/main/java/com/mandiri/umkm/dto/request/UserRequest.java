package com.mandiri.umkm.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String address;
}
