package com.devcoresolutions.thepixelvault.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


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

    @Column(nullable = false,columnDefinition = "TINYINT DEFAULT 1")
    private Byte status = 1;

    @Column(nullable = false)
    private String game;

    @Column
    private String brand;

    @Column
    private String attributes;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderHasProduct> orderHasProducts;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "product_has_categories",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "categories_id")
    )
    // Para una relación muchos a muchos se recomienda utilizar sets
    private Set<Categories> categories = new HashSet<>();

    public void addCategory(Categories category){
        this.categories.add(category);
        category.getProducts().add(this);
    }

    public void removeCategory(Categories category){
        this.categories.remove(category);
        category.getProducts().remove(this);
    }
}
