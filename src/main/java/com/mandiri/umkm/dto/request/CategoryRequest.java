package com.mandiri.umkm.dto.request;

import com.mandiri.umkm.entity.Category;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryRequest {
    private String name;
    private String description;
    private Category.Type type;
}
