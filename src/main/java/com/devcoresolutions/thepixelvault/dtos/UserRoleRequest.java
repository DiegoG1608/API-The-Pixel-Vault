package com.devcoresolutions.thepixelvault.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRoleRequest(
        @NotBlank(message = "El nombre del rol no puede estar vacío.")
        @Size(max = 50, message = "El nombre del rol no puede superar los 50 caracteres.")
        String roleName) {
}
