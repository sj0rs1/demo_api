package com.example.demo_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class ProductRequestDTO {

    @NotBlank(message = "Name is required")
    private String name;

    @Positive(message = "Price must be greater than 0")
    private double price;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}