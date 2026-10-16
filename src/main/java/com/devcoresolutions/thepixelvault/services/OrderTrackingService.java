package com.devcoresolutions.thepixelvault.services;

import com.devcoresolutions.thepixelvault.dtos.OrderTrackingRequest;
import com.devcoresolutions.thepixelvault.exceptions.ResourceNotFoundException;
import com.devcoresolutions.thepixelvault.models.OrderTracking;
import com.devcoresolutions.thepixelvault.models.Orders;
import com.devcoresolutions.thepixelvault.repository.OrderTrackingRepository;
import com.devcoresolutions.thepixelvault.repository.OrdersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderTrackingService {

    private final OrderTrackingRepository orderTrackingRepository;
    private final OrdersRepository ordersRepository;

    //Obtener todos los seguimientos
    public List<OrderTracking> getAllOrderTracking() {
        return orderTrackingRepository.findAll();
    }

    // Obtener seguimiento por ID
    public OrderTracking getOrderTrackingById(Long id) {
        return orderTrackingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tracking no encontrado con ID: " + id));
    }

    // Obtener seguimiento por Estatus
    public List<OrderTracking> getOrderTrackingByStatus(String status) {
        return orderTrackingRepository.findByOrderStatus(status);
    }

    // Crear seguimiento
    public OrderTracking createOrderTracking(OrderTrackingRequest request) {
        //Buscamos que la orden (Orders) exista en la base de datos
        Orders order = ordersRepository.findById(request.orderId())
                .orElseThrow(() -> new ResourceNotFoundException("Orden no encontrada con ID: " + request.orderId()));

        // Creamos la nueva entidad y le asignamos los valores del DTO
        OrderTracking orderTracking = new OrderTracking();
        orderTracking.setEstimatedDeliveryDate(request.estimatedDeliveryDate());
        orderTracking.setOrderStatus(request.orderStatus());
        orderTracking.setOrders(order);

        return orderTrackingRepository.save(orderTracking);
    }

    //Actualiza seguimiento existente
    public OrderTracking updateOrderTracking(Long id, OrderTrackingRequest request) {

        OrderTracking existingTracking = getOrderTrackingById(id);


        Orders order = ordersRepository.findById(request.orderId())
                .orElseThrow(() -> new ResourceNotFoundException("Orden no encontrada con ID: " + request.orderId()));


        existingTracking.setEstimatedDeliveryDate(request.estimatedDeliveryDate());
        existingTracking.setOrderStatus(request.orderStatus());
        existingTracking.setOrders(order);

        return orderTrackingRepository.save(existingTracking);
    }

    // Eliminar seguimiento
    public void deleteOrderTracking(Long id) {
        OrderTracking existingTracking = getOrderTrackingById(id);
        orderTrackingRepository.delete(existingTracking);
    }
}
