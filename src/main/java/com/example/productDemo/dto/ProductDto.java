package com.example.productDemo.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    @NotNull(message = "Name is required.")
    @NotEmpty(message = "Name can not be empty.")
    private String name;

    @NotNull(message = "Description is required.")
    @NotEmpty(message = "Description can not be empty.")
    private String description;

    @NotNull(message = "Price is required.")
    @Min(value = 0, message = "Price must be zero or a positive number.")
    @Digits(integer = 10, fraction = 2, message = "Price must be up to 10 digits and 2 decimal places.")
    private String price;
}
