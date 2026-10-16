package com.devcoresolutions.thepixelvault.controllers;

import com.devcoresolutions.thepixelvault.dtos.OrderHasProductRequest;
import com.devcoresolutions.thepixelvault.models.OrderHasProduct;
import com.devcoresolutions.thepixelvault.services.OrderHasProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-products")
@CrossOrigin(origins = "*") // Ajusta según tus necesidades de CORS para el frontend
public class OrderHasProductController {

    @Autowired
    private OrderHasProductService orderHasProductService;

    @GetMapping
    public ResponseEntity<List<OrderHasProduct>> getAllOrderProducts() {
        return ResponseEntity.ok(orderHasProductService.findAll());
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<List<OrderHasProduct>> getProductsByOrder(@PathVariable Integer orderId) {
        return ResponseEntity.ok(orderHasProductService.findByOrderId(orderId));
    }

    @PostMapping
    public ResponseEntity<OrderHasProduct> addProductToOrder(@Valid @RequestBody OrderHasProductRequest request) {
        OrderHasProduct savedRelation = orderHasProductService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRelation);
    }

    @DeleteMapping("/order/{orderId}/product/{productId}")
    public ResponseEntity<Void> removeProductFromOrder(
            @PathVariable Integer orderId,
            @PathVariable Integer productId) {
        orderHasProductService.delete(orderId, productId);
        return ResponseEntity.noContent().build();
    }
}