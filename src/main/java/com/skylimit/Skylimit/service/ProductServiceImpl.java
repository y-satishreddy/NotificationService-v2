package com.skylimit.Skylimit.service;

import com.skylimit.Skylimit.dto.notification.NotificationRequest;
import com.skylimit.Skylimit.dto.notification.NotificationResponse;
import com.skylimit.Skylimit.dto.product.*;
import com.skylimit.Skylimit.entity.Product;
import com.skylimit.Skylimit.exception.ProductNotFoundException;
import com.skylimit.Skylimit.mapper.ProductMapper;
import com.skylimit.Skylimit.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final RestClient restClient;
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(RestClient builder, ProductRepository productRepository, ProductMapper productMapper) {
        this.restClient = builder;
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public ProductAddProductResponseDTO saveProduct(ProductAddProductRequestDTO productDTO) {
        log.info("Save product method is calling");
        Product repoProduct = productRepository.save(productMapper.toEntity(productDTO));
        log.info("Product saved successfully");
        log.info("Notifcation service is calling");
        NotificationRequest notificationRequest=new NotificationRequest(repoProduct.getId(), "ADD_PRODUCT", "Product added successfully");
        NotificationResponse notificationResponse=restClient.post().uri("http://localhost:8080/notifications")
                .header("correlationId", "AddProduct").body(notificationRequest).retrieve()
                .body(NotificationResponse.class);
        log.info("{}",notificationResponse);
        return productMapper.toAddResponse(repoProduct);
    }

    public ProductGetProductResponseDTO getProduct(Long id) {
        log.info("Find product by id is called with id: {}", id);
        Product repoProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id : " + id));
        log.info("Product found successfully");
        log.info("Notifcation Service is calling");
        NotificationRequest notificationRequest = new NotificationRequest(id, "GET_PRODUCT",
                "Product fetched successfully");
        NotificationResponse notificationResponse = restClient.post().uri("http://localhost:8080/notifications")
                .header("correlationId", "GetProduct").body(notificationRequest).retrieve()
                .body(NotificationResponse.class);
        log.info("{}",notificationResponse);
        return productMapper.toGetDetailsResponse(repoProduct);
    }

    public ProductGetProductResponseDTO partialUpdateProduct(
            Long id,
            ProductPartialUpdate dto) {
        log.info("Partial Upadte is calling with id: {}", id);
        Product repoProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id : " + id));

        if (dto.getName() != null) {
            repoProduct.setName(dto.getName());
        }

        if (dto.getSlug() != null) {
            repoProduct.setSlug(dto.getSlug());
        }

        if (dto.getCategoryId() != null) {
            repoProduct.setCategoryId(dto.getCategoryId());
        }

        if (dto.getBrandName() != null) {
            repoProduct.setBrandName(dto.getBrandName());
        }

        if (dto.getPrice() != null) {
            repoProduct.setPrice(dto.getPrice());
        }

        if (dto.getMrp() != null) {
            repoProduct.setMrp(dto.getMrp());
        }

        if (dto.getShortDescription() != null) {
            repoProduct.setShortDescription(dto.getShortDescription());
        }

        if (dto.getLongDescription() != null) {
            repoProduct.setLongDescription(dto.getLongDescription());
        }

        if (dto.getActive() != null) {
            repoProduct.setActive(dto.getActive());
        }

        if (dto.getFeatured() != null) {
            repoProduct.setFeatured(dto.getFeatured());
        }

        Product updatedProduct = productRepository.save(repoProduct);
        log.info("Partial Update for product with id {} is completed successfully", id);
        log.info("Notification Service is calling for product partial update");
        NotificationRequest notificationRequest = new NotificationRequest(id, "PARTIAL_UPDATE",
                "Product partial update is completed");
        NotificationResponse notificationResponse = restClient.post().uri("http://localhost:8080/notifications")
                .header("correlationId", "PartialProductUpdate").body(notificationRequest).retrieve()
                .body(NotificationResponse.class);
        log.info("{}", notificationResponse);
        return productMapper.toGetDetailsResponse(updatedProduct);
    }

    public ProductGetProductResponseDTO updateProduct(Long id, ProductUpdateRequest productUpdateRequest) {

        log.info("Product update is calling with id: {}", id);
        Product repoProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found with id : " + id));
        repoProduct.setName(productUpdateRequest.getName());
        repoProduct.setSlug(productUpdateRequest.getSlug());
        repoProduct.setCategoryId(productUpdateRequest.getCategoryId());
        repoProduct.setBrandName(productUpdateRequest.getBrandName());
        repoProduct.setPrice(productUpdateRequest.getPrice());
        repoProduct.setMrp(productUpdateRequest.getMrp());
        repoProduct.setShortDescription(productUpdateRequest.getShortDescription());
        repoProduct.setLongDescription(productUpdateRequest.getLongDescription());
        repoProduct.setActive(productUpdateRequest.getActive());
        repoProduct.setFeatured(productUpdateRequest.getFeatured());
        Product response = productRepository.save(repoProduct);
        log.info("Product updated successfully");
        log.info("Notification API is called");
        NotificationRequest notificationRequest = new NotificationRequest(id, "RODUCT_UPDATE",
                "Product updated succesfully");
        NotificationResponse notificationResponse = restClient.post().uri("http://localhost:8080/notifications")
                .header("correlationId", "Update").body(notificationRequest).retrieve()
                .body(NotificationResponse.class);
        log.info("{}", notificationResponse);
        return productMapper.toGetDetailsResponse(response);
    }

    public ProductGetProductResponseDTO deleteProduct(Long id) {
        log.info("Deleting product with id: {}", id);
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found to delete"));
        productRepository.deleteById(id);
        log.info("Product successfully deleted, id: {}", id);
        log.info("Notification API is called");
        NotificationRequest notificationRequest = new NotificationRequest(id, "DELETE_PRODUCT",
                "Product deleted successfully");
        NotificationResponse notificationResponse = restClient.post().uri("http://localhost:8080/notifications")
                .header("correlationId", "Delete").body(notificationRequest).retrieve()
                .body(NotificationResponse.class);
        log.info("{}", notificationResponse.toString());
        return productMapper.toGetDetailsResponse(product);
    }

}