package com.example.demo_api.exception;

public class FruitNotFoundException extends RuntimeException {
    public FruitNotFoundException(Long id) {
        super("Fruit not found with id: " + id);
    }
}