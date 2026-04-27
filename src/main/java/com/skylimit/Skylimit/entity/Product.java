package com.skylimit.Skylimit.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Product {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 500, nullable = false)
    private String name;

    @Column(name = "slug", length = 500, nullable = false, unique = true)
    private String slug;

    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    @Column(name = "brand_name", nullable = true)
    private String brandName;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "mrp", nullable = true, precision = 10, scale = 2)
    private BigDecimal mrp;

    @Column(name = "short_description", length = 500, nullable = true)
    private String shortDescription;

    @Column(name = "long_description", nullable = true, columnDefinition = "TEXT")
    private String longDescription;

    @Column(name = "is_active", nullable = false)
    private Boolean active = true;

    @Column(name = "is_featured", nullable = false)
    private Boolean featured = false;

    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at", updatable = false, insertable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
