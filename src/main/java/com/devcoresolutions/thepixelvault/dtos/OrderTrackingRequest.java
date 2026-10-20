package com.devcoresolutions.thepixelvault.dtos;


import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record OrderTrackingRequest (
        LocalDate estimatedDeliveryDate,
        String orderStatus,
        @NotNull
        Long orderId
) {
}
