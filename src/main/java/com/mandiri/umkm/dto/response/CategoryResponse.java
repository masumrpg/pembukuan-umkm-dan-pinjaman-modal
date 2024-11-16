package com.mandiri.umkm.dto.response;

import com.mandiri.umkm.entity.Category;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryResponse {
    private String id;
    private String name;
    private String description;
    private Category.Type type;
    private String createdAt;
    private String updatedAt;
}
