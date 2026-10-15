package com.devcoresolutions.thepixelvault.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 250)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false, length = 250)
    private String image;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Integer rating;

    @Column(nullable = false)
    private Integer stock;

    @Column(nullable = false, columnDefinition = "default 1")
    private Byte status = 1;

    @Column(nullable = false)
    private String game;

    @Column
    private String brand;

    @Column
    private String attributes;
}
