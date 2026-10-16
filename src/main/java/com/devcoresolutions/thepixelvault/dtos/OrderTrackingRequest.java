package com.devcoresolutions.thepixelvault.dtos;

import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public record OrderTrackingRequest (
        Date estimatedDeliveryDate,
        String orderStatus,
        @NotBlank
        Long orderId
) {
}
