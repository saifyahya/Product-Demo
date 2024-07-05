package com.example.productDemo.mapper;

import com.example.productDemo.dto.ProductDto;
import com.example.productDemo.entity.Product;

public class ProductMapper {

    public static ProductDto toProductDto(Product product,ProductDto productDto) {
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice()+"");
        return productDto;
    }
    public static Product fromProductDto(ProductDto productDto,Product product) {
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(Double.parseDouble(productDto.getPrice()));
        return product;
    }
}
