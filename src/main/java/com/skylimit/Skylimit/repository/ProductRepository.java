package com.skylimit.Skylimit.repository;

import com.skylimit.Skylimit.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
