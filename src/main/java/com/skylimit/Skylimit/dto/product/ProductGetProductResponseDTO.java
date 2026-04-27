package com.skylimit.Skylimit.dto.product;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ProductGetProductResponseDTO {

    private Long id;
    private String name;
    private String slug;
    private Long categoryId;
    private String brandName;
    private BigDecimal price;
    private BigDecimal mrp;
    private String shortDescription;
    private String longDescription;
    private final Boolean active;
    private final Boolean featured;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}


