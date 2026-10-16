package com.devcoresolutions.thepixelvault.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsersRequest(
        @NotBlank(message = "No puede estar en blanco el email")
        @Size(max = 100)
        String email,
        @NotBlank(message = "No puede estar en blanco el password")
        @Size(max = 255)
        String password,
        @NotBlank(message = "No puede estar en blanco el user name")
        @Size(max = 100)
        String userName,
        @NotBlank(message = "No puede estar en blanco el middle name")
        @Size(max = 50)
        String userMiddleName,
        @NotBlank(message = "No puede estar en blanco el teléfono")
        @Size(max = 13)
        String userPhone,
        @NotBlank(message = "No puede estar en blanco el last name")
        @Size(max = 100)
        String userLastNameFirst,
        @NotBlank(message = "No puede estar en blanco el status")
        @Max(1)
        Byte status,
        @NotBlank(message = "No puede estar en blanco lastname")
        @Size(max = 100)
        String userLastNameSecond,
        UserAddress userAddress
) {
}
