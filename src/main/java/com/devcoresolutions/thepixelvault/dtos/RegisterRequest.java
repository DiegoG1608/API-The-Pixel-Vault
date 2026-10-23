package com.devcoresolutions.thepixelvault.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @NotBlank @Email @Size(max = 100) String email,
        @NotBlank @Size(min = 8, max = 20) String password,
        @NotBlank @Size(max = 100) String userName,
        @NotBlank @Size(max = 50) String userMiddleName,
        @NotBlank @Size(max = 13) String userPhone,
        @NotBlank @Size(max = 100) String userLastNameFirst,
        Byte status, // se ignora en el registro: lo asigna el servidor
        @NotBlank @Size(max = 100) String userLastNameSecond,
        UserAddress userAddress
) {}