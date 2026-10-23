package com.devcoresolutions.thepixelvault.dtos;

import java.util.ArrayList;

public record OrderDataRequest(
        OrdersRequest ordersRequest,
        OrderTrackingRequest orderTrackingRequest,
        ArrayList<OrderHasProductRequest> listOrderHasProduct
) {
}
