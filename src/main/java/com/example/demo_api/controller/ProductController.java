package com.example.demo_api.controller;

import com.example.demo_api.dto.ProductRequestDTO;
import com.example.demo_api.dto.ProductResponseDTO;
import com.example.demo_api.exception.ProductNotFoundException;
import com.example.demo_api.model.Product;
import com.example.demo_api.repository.ProductRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    private ProductResponseDTO toResponseDTO(Product product) {
        return new ProductResponseDTO(product.getId(), product.getName(), product.getPrice());
    }

    @GetMapping
    public List<ProductResponseDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public ProductResponseDTO getProductById(@PathVariable Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        return toResponseDTO(product);
    }

    @PostMapping
    public ProductResponseDTO createProduct(@Valid @RequestBody ProductRequestDTO request) {
        Product product = new Product();
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        Product saved = productRepository.save(product);
        return toResponseDTO(saved);
    }

    @PutMapping("/{id}")
    public ProductResponseDTO updateProduct(@PathVariable Long id, @Valid @RequestBody ProductRequestDTO request) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        Product saved = productRepository.save(product);
        return toResponseDTO(saved);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productRepository.deleteById(id);
    }
}