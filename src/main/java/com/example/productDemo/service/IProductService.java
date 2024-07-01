package com.example.productDemo.service;

import com.example.productDemo.dto.ProductDto;

import java.util.List;

public interface IProductService {

    void saveProduct(ProductDto productDto);
    ProductDto getProductById(long id);

    List<ProductDto> getAllProducts();

    void deleteProduct(long id);

    boolean updateProduct(ProductDto productDto,long id);


}
