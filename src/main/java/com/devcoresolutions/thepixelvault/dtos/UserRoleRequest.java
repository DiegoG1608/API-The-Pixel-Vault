package com.devcoresolutions.thepixelvault.dtos;

import jakarta.validation.constraints.NotBlank;

public record UserRoleRequest(
        @NotBlank
        String roleName) {
}
