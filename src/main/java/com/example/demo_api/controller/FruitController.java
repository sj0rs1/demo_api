package com.example.demo_api.controller;

import com.example.demo_api.exception.FruitNotFoundException;
import com.example.demo_api.model.Fruit;
import com.example.demo_api.repository.FruitRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import com.example.demo_api.dto.FruitRequestDTO;
import com.example.demo_api.dto.FruitResponseDTO;

import java.util.List;

@RestController
@RequestMapping("/api/fruit")
public class FruitController {

    private final FruitRepository fruitRepository;

    public FruitController(FruitRepository fruitRepository){
        this.fruitRepository = fruitRepository;
    }

    private FruitResponseDTO toResponseDTO(Fruit fruit) {
        return new FruitResponseDTO(fruit.getId(), fruit.getName());
    }

    @GetMapping
    public List<FruitResponseDTO> getAllFruit() {
        return fruitRepository.findAll().stream().map(this::toResponseDTO).toList();
    }

    @GetMapping("/{id}")
    public FruitResponseDTO getFruitById(@PathVariable Long id) {
        Fruit fruit = fruitRepository.findById(id)
                .orElseThrow(() -> new FruitNotFoundException(id));
        return toResponseDTO(fruit);
    }

    @PostMapping
    public FruitResponseDTO createFruit(@Valid @RequestBody FruitRequestDTO request){
        Fruit fruit = new Fruit();
        fruit.setName(request.getName());
        Fruit saved = fruitRepository.save(fruit);
        return toResponseDTO(saved);
    }

    @PutMapping("/{id}")
    public FruitResponseDTO updateFruit(@PathVariable Long id, @Valid @RequestBody FruitRequestDTO request){
        Fruit fruit = fruitRepository.findById(id).orElseThrow(() -> new FruitNotFoundException(id));
        fruit.setName(request.getName());
        Fruit saved = fruitRepository.save(fruit);
        return toResponseDTO(saved);
    }

    @DeleteMapping("/{id}")
    public void deleteFruit(@PathVariable Long id) { fruitRepository.deleteById(id); }
}