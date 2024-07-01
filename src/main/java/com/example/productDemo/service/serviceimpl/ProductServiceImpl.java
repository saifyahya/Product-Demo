package com.example.productDemo.service.serviceimpl;

import com.example.productDemo.dto.ProductDto;
import com.example.productDemo.entity.Product;
import com.example.productDemo.mapper.ProductMapper;
import com.example.productDemo.repository.ProductRepository;
import com.example.productDemo.service.IProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@AllArgsConstructor
public class ProductServiceImpl implements IProductService {

    private ProductRepository productRepository;

    @Override
    public void saveProduct(ProductDto productDto) {
        Product product = ProductMapper.fromProductDto(productDto);
        productRepository.save(product);
    }

    @Override
    public ProductDto getProductById(long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product Not Found With ID: " + id));
        ProductDto productDto = ProductMapper.toProductDto(product);
        return productDto;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();
        products.forEach((Product p) -> {
            ProductDto productDto = ProductMapper.toProductDto(p);
            productDtos.add(productDto);
        });

        return productDtos;
    }

    @Override
    public void deleteProduct(long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product Not Found With ID: " + id));
        productRepository.delete(product);
    }

    @Override
    public boolean updateProduct(ProductDto productDto, long id) {
        boolean isUpdated = false;
        if (productDto != null) {
            Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product Not Found With ID: " + id));
            Product updatedProduct = ProductMapper.fromProductDto(productDto);
            productRepository.save(updatedProduct);
            isUpdated = true;
        }
        return isUpdated;
    }
}
