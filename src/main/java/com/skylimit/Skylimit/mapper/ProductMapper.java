package com.skylimit.Skylimit.mapper;

import com.skylimit.Skylimit.dto.product.ProductAddProductRequestDTO;
import com.skylimit.Skylimit.dto.product.ProductAddProductResponseDTO;
import com.skylimit.Skylimit.dto.product.ProductGetProductResponseDTO;
import com.skylimit.Skylimit.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public Product toEntity(ProductAddProductRequestDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setSlug(productDTO.getSlug());
        product.setCategoryId(productDTO.getCategoryId());
        product.setPrice(productDTO.getPrice());
        product.setMrp(productDTO.getMrp());
        product.setActive(productDTO.getActive());
        product.setFeatured(productDTO.getFeatured());
        product.setLongDescription(productDTO.getLongDescription());
        product.setShortDescription(productDTO.getShortDescription());
        product.setBrandName(productDTO.getBrandName());
        return product;
    }

    public ProductAddProductResponseDTO toAddResponse(Product product) {
        return new ProductAddProductResponseDTO(product.getId(), product.getName(), product.getSlug(), product.getCategoryId(), product.getBrandName(), product.getPrice(), product.getMrp(), product.getShortDescription(), product.getLongDescription(), product.getActive(), product.getFeatured(), product.getCreatedAt());

    }

    public ProductGetProductResponseDTO toGetDetailsResponse(Product product){
        return new ProductGetProductResponseDTO(product.getId(), product.getName(), product.getSlug(), product.getCategoryId(), product.getBrandName(), product.getMrp(), product.getPrice(), product.getShortDescription(), product.getLongDescription(), product.getActive(), product.getFeatured(), product.getCreatedAt(), product.getUpdatedAt());

    }
}
