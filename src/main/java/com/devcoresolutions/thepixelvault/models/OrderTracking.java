package com.devcoresolutions.thepixelvault.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

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
    private LocalDate estimatedDeliveryDate;

    @Column(nullable = false, length = 50, name = "order_status")
    private String orderStatus;

    @ManyToOne
    @JoinColumn(name = "order_idorder", nullable = false)
    @JsonIgnore
    private Orders orders;


}
//OrderHasProduct
