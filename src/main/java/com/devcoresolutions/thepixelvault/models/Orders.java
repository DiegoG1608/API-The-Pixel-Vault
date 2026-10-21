package com.devcoresolutions.thepixelvault.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_date", nullable = false) // Es buena práctica usar snake_case para la BD
    private Date orderDate;

    @Column(nullable = false)
    private Double total;

    // Muchas ordenes pertenecen a un único usuario (Unión por clave primaria simple)
    @ManyToOne
    @JoinColumn(name = "user_iduser", referencedColumnName = "iduser", nullable = false)
    @JsonIgnore
    private Users users;

    // Una orden puede tener muchos productos
    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderHasProduct> orderHasProducts = new ArrayList<>();

    // Una orden puede tener varios estados de rastreo
    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderTracking> orderTrackings = new ArrayList<>();
}
