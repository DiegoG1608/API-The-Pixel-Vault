package com.devcoresolutions.thepixelvault.controllers;

import com.devcoresolutions.thepixelvault.dtos.OrdersRequest;
import com.devcoresolutions.thepixelvault.models.Orders;
import com.devcoresolutions.thepixelvault.services.OrdersService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/api/orders")
@AllArgsConstructor
public class OrdersControllers {

    private final OrdersService orderService;

//Obtener todas las ordenes de este usuario, usando su id
    @GetMapping(path = "users/{usersId}/orders")
    public ResponseEntity<List<Orders>> getAllByOrdersId(@PathVariable("usersId") Long id){
        List<Orders> ordersList = orderService.getAllByOrdersId(id);
        return ResponseEntity.ok(ordersList);
    }

    //Obtener una orden de este usuario por medio de su id
    @GetMapping(path = "/users/{usersId}/orders/{orderId}")
    public ResponseEntity<Orders> getOrderByUserIdAndOrderId(
            @PathVariable("usersId") Long id,
            @PathVariable("orderId") Long orderId){
        Orders order = orderService.getOrderByUserAndOrderId(id, orderId);
        return ResponseEntity.ok(order);
    }
    //Crea una orden para este usuario por medio de su id
    @PostMapping(path = "users/{usersId}/orders")
    public ResponseEntity<Orders> createOrderByUserId(@PathVariable("usersId") Long id,
                                                      @RequestBody OrdersRequest ordersRequest){
        Orders orders = orderService.createOrderByUserId(id, ordersRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(orders.getId())
                .toUri();
        return ResponseEntity.created(location).body(orders);
    }

}
