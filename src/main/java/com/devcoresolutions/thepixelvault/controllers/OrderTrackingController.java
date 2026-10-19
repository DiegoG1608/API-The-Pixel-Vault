package com.devcoresolutions.thepixelvault.controllers;

import com.devcoresolutions.thepixelvault.dtos.OrderTrackingRequest;
import com.devcoresolutions.thepixelvault.models.OrderTracking;
import com.devcoresolutions.thepixelvault.services.OrderTrackingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/orderTracking")
@AllArgsConstructor
public class OrderTrackingController {

    private final OrderTrackingService orderTrackingService;

    @GetMapping
    public ResponseEntity<List<OrderTracking>> getAllOrderTracking() {
        return ResponseEntity.ok(orderTrackingService.getAllOrderTracking());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderTracking> getOrderTrackingById(@PathVariable Long id) {
        return ResponseEntity.ok(orderTrackingService.getOrderTrackingById(id));
    }

    @PostMapping
    public ResponseEntity<OrderTracking> createOrderTracking(@RequestBody OrderTrackingRequest orderTrackingRequest) {
        OrderTracking createdTracking = orderTrackingService.createOrderTracking(orderTrackingRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdTracking.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdTracking);
    }

    /**
     *
     * @param id Se espera Id del trackingOrder
     * @param orderTrackingRequest
     * @return
     */

    @PutMapping("/{id}")
    public ResponseEntity<OrderTracking> updateOrderTracking(@PathVariable Long id, @RequestBody OrderTrackingRequest orderTrackingRequest) {
        return ResponseEntity.ok(orderTrackingService.updateOrderTracking(id, orderTrackingRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderTracking(@PathVariable Long id) {
        orderTrackingService.deleteOrderTracking(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<OrderTracking>> getOrderTrackingByStatus(@PathVariable String status) {
        return ResponseEntity.ok(orderTrackingService.getOrderTrackingByStatus(status));
    }
}
