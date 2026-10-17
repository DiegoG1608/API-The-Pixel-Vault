package com.devcoresolutions.thepixelvault.services;


import com.devcoresolutions.thepixelvault.dtos.OrderHasProductRequest;
import com.devcoresolutions.thepixelvault.models.OrderHasProduct;
import com.devcoresolutions.thepixelvault.models.Orders;
import com.devcoresolutions.thepixelvault.models.Product;
import com.devcoresolutions.thepixelvault.repository.OrderHasProductRepository;
import com.devcoresolutions.thepixelvault.repository.OrdersRepository;
import com.devcoresolutions.thepixelvault.repository.ProductRepository;
import jakarta.persistence.criteria.Order;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
@AllArgsConstructor
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

    public List<OrderHasProduct> findByOrderId(Long orderId) {
        return orderHasProductRepository.findByOrders(orderId);
    }

    @Transactional
    public OrderHasProduct save(OrderHasProductRequest request) {
        // 1. Buscamos la orden
        Orders orders = orderRepository.findById(Long.valueOf(request.getOrderId()))
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado con ID: " + request.getOrderId()));

        // 2. Buscamos el producto
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + request.getProductId()));

        // 3. Creamos la relación y asignamos sus campos
        OrderHasProduct orderHasProduct = new OrderHasProduct();
        orderHasProduct.setOrders(orders); // ¡Nota la 'o' minúscula en lugar del cero!
        orderHasProduct.setProduct(product);
        orderHasProduct.setOrderQuantity(request.getOrderQuantity());

        // Si no envían precio de compra, tomamos el precio actual del producto
        orderHasProduct.setPurchasePrice(
                request.getPurchasePrice() != null ? request.getPurchasePrice() : product.getPrice()
        );

        // 4. Guardamos directamente
        return orderHasProductRepository.save(orderHasProduct);
    }

    @Transactional
    public void delete(Long orderHasProductId) {
        if (!orderHasProductRepository.existsById(orderHasProductId)) {
            throw new RuntimeException("La relación pedido-producto no existe con ID: " + orderHasProductId);
        }
        orderHasProductRepository.deleteById(orderHasProductId);
    }
}