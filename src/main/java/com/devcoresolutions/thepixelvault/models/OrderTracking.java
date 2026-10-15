package com.devcoresolutions.thepixelvault.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class OrderTracking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "idorder_tracking")
    private Long id;

    @Column(nullable = false, name = "estimated_delivery_date")
    private Date estimatedDeliveryDate;

    @Column(nullable = false, length = 50, name = "order_status")
    private String orderStatus;

    @ManyToOne
    @JoinColumn(name = "order_idorder", nullable = false)
    private Orders orders;

}
