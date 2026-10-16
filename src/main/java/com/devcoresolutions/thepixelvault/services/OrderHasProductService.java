package com.devcoresolutions.thepixelvault.services;


import com.devcoresolutions.thepixelvault.dtos.OrderHasProductRequest;
import com.devcoresolutions.thepixelvault.models.OrderHasProduct;
import com.devcoresolutions.thepixelvault.models.Product;
import com.devcoresolutions.thepixelvault.repository.OrderHasProductRepository;
import com.devcoresolutions.thepixelvault.repository.OrdersRepository;
import jakarta.persistence.criteria.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderHasProductService {

    @Autowired
    private final OrderHasProductRepository orderHasProductRepository;

    @Autowired
    private  final OrdersRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<OrderHasProduct> findAll() {
        return orderHasProductRepository.findAll();
    }

    public List<OrderHasProduct> findByOrderId(Integer orderId) {
        return orderHasProductRepository.findById_OrderIdorder(orderId);
    }

    @Transactional
    public OrderHasProduct save(OrderHasProductRequest request) {
        Order order = orderRepository.findById(Long.valueOf(request.getOrderId()))
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con ID: " + request.getOrderId()));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + request.getProductId()));

        OrderHasProduct orderHasProduct = new OrderHasProduct();
        OrderProductKey key = new OrderProductKey(order.getIdorder(), product.getIdproduct());

        orderHasProduct.setId(key);
        orderHasProduct.setOrder(order);
        orderHasProduct.setProduct(product);
        orderHasProduct.setOrderQuantity(request.getOrderQuantity());

        // Si no mandan precio de compra, tomamos el precio actual del producto
        orderHasProduct.setPurchasePrice(
                request.getPurchasePrice() != null ? request.getPurchasePrice() : product.getProductPrice()
        );

        return orderHasProductRepository.save(orderHasProduct);
    }

    @Transactional
    public void delete(Integer orderId, Integer productId) {
        OrderProductKey key = new OrderProductKey(orderId, productId);
        if (!orderHasProductRepository.existsById(key)) {
            throw new RuntimeException("La relación pedido-producto no existe");
        }
        orderHasProductRepository.deleteById(key);
    }
}