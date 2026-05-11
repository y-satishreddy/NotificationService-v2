package com.skylimit.Skylimit.controller;

import com.skylimit.Skylimit.dto.product.*;
import com.skylimit.Skylimit.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@Slf4j
@AllArgsConstructor
public class ProductController {

    private final ProductService productServiceImpl;
    @PostMapping
    public ProductAddProductResponseDTO saveProduct(@Valid @RequestBody ProductAddProductRequestDTO product) {
        log.info("POST /products called");
        ProductAddProductResponseDTO response = productServiceImpl.saveProduct(product);
        log.debug("Product is saved successfully with id : {}, name : {}", response.getId(), response.getName());
        log.info("Product saved successfully with id = {}", response.getId());
        return response;
    }

    @GetMapping("/{id}")
    @Cacheable(value="products",key="#id")
    public ProductGetProductResponseDTO getProduct(@PathVariable Long id) {
        log.info("GET /products called with ", id);
        log.debug("Fetching product details for id: {}", id);
        ProductGetProductResponseDTO response = productServiceImpl.getProduct(id);
        log.info("Product details returned for id = {}", id);
        return response;
    }
    @PatchMapping("/{id}")
    @CachePut(value="products", key="#id")
    public ProductGetProductResponseDTO partialUpdateProduct(
            @PathVariable Long id,
            @RequestBody ProductPartialUpdate productPartialUpdateDTO
    ) {

        log.info("PATCH /products called with id = {}", id);

        ProductGetProductResponseDTO response =
                productServiceImpl.partialUpdateProduct(id, productPartialUpdateDTO);

        log.info("Product updated successfully with id = {}", id);

        return response;
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProductGetProductResponseDTO> updateProduct(@PathVariable Long id, @RequestBody ProductUpdateRequest produtUpdateRequest){
        log.info("PUT /products is called with id = {}", id);
        ProductGetProductResponseDTO response=productServiceImpl.updateProduct(id, produtUpdateRequest);
        log.info("Product updated successfully with id = {}", id);
        return ResponseEntity.ok(response);
    }
    @CacheEvict(value = "products", key="#id")
    @DeleteMapping("/{id}")
    public ResponseEntity<ProductGetProductResponseDTO> deleteProduct(@PathVariable Long id){
        log.info("DELETE /products api is called with id = {}", id);
        ProductGetProductResponseDTO response = productServiceImpl.deleteProduct(id);
        log.info("Response returned successfully");
        return ResponseEntity.ok(response);
        
    }
}
