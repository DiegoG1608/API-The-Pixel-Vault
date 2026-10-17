package com.devcoresolutions.thepixelvault.dtos;

import jakarta.validation.constraints.NotBlank;

public record CategoriesRequest(
        @NotBlank
        String categoryName,
        String categoryDescription
) {
}
