package com.devcoresolutions.thepixelvault.dtos;

import jakarta.validation.constraints.NotBlank;

public record ProductRequest (
        @NotBlank
        String name,
        @NotBlank
        String description,
        @NotBlank
        String image,
        @NotBlank
        Double price,
        @NotBlank
        Integer rating,
        @NotBlank
        Integer stock,
        @NotBlank
        String game,
        String brand,
        String attributes
) {
}
