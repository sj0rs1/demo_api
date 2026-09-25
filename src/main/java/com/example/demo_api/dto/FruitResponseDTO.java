package com.example.demo_api.dto;

public class FruitResponseDTO {
    private Long id;
    private String name;

    public FruitResponseDTO(Long id, String name){
        this.id = id;
        this.name = name;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
}