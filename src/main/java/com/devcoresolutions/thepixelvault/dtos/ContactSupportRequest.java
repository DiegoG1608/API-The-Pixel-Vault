package com.devcoresolutions.thepixelvault.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

public record ContactSupportRequest(

        @NotBlank(message = "El mensaje no puede estar vacio")
        String message,

        @NotBlank(message = "El nombre no puede estar vacio")
        String fullName,

        @NotBlank(message = "El email no puede estar vacio")
        String email,

        String noUserPhone
) {
}
