package com.mandiri.umkm.dto.response;

import com.mandiri.umkm.entity.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private String id;
    private String username;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String address;
    private String createdAt;
    private String updatedAt;
    private User.Status status;
}
