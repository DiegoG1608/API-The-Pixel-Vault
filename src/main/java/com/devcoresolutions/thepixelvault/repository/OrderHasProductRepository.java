package com.devcoresolutions.thepixelvault.repository;


import com.devcoresolutions.thepixelvault.models.OrderHasProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderHasProductRepository extends JpaRepository<OrderHasProduct, Long> {

    // Buscar todos los productos asociados a un pedido específico
    List<OrderHasProduct> findById_OrderIdorder(Integer orderId);

    // Buscar en qué pedidos ha estado un producto
    List<OrderHasProduct> findById_ProductIdproduct(Integer productId);
}