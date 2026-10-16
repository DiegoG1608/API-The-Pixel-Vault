package com.devcoresolutions.thepixelvault.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class OrderHasProductRequest {

    @NotNull(message = "El ID del pedido es obligatorio")
    private Integer orderId;

    @NotNull(message = "El ID del producto es obligatorio")
    private Integer productId;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser al menos 1")
    private Integer orderQuantity;

    private BigDecimal purchasePrice;
}