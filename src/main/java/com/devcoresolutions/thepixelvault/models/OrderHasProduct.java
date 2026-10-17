package com.devcoresolutions.thepixelvault.models;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OrderHasProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_quantity", nullable = false)
    private Integer orderQuantity;

    @Column(name = "purchase_price")
    private Double purchasePrice;

    // Relación ManyToOne hacia la orden
    @ManyToOne
    @JoinColumn(name = "order_idorder", nullable = false)
    private Orders orders;

    // Relación ManyToOne hacia el producto
    @ManyToOne
    @JoinColumn(name = "product_idproduct", nullable = false)
    private Product product;
}
