package com.example.productDemo.controller;

import com.example.productDemo.dto.ProductDto;
import com.example.productDemo.dto.ResponseDto;
import com.example.productDemo.service.IProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private IProductService productService;

    @GetMapping("")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> productDtos = productService.getAllProducts();
        ResponseEntity responseEntity = new ResponseEntity<>(
                productDtos, HttpStatus.OK
        );
        return responseEntity;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable long id) {
        ProductDto productDto = productService.getProductById(id);
        ResponseEntity responseEntity = new ResponseEntity<>(
                productDto, HttpStatus.OK
        );
        return responseEntity;
    }

    @PostMapping("")
    public ResponseEntity<ResponseDto> saveProduct(@RequestBody @Valid ProductDto productDto) {
        productService.saveProduct(productDto);

        return new ResponseEntity<>(new ResponseDto(HttpStatus.CREATED.toString(), "Product Saved Successfully."), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateProduct(@PathVariable long id, @RequestBody @Valid ProductDto productDto) {
        boolean isUpdated = productService.updateProduct(productDto, id);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.OK.toString(), "Product with ID: " + id + " Updated Successfully."));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Update operation failed."));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteProduct(@PathVariable long id) {
        productService.deleteProduct(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(HttpStatus.OK.toString(), "Product with ID: " + id + " Deleted Successfully."));
    }

}
