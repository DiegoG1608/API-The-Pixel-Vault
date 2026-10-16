package com.devcoresolutions.thepixelvault.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record OrdersRequest(
        @NotBlank(message = "No puede estar en blanco la fecha")
        Date orders_date,
        @NotBlank(message = "No puede estar en blanco el total de la orden")
        Double total
) {
}
