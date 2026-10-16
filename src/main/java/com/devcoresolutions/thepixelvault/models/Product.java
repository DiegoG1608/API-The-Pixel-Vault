package com.devcoresolutions.thepixelvault.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Column(nullable = false, columnDefinition = "default 1")
    private Byte status = 1;

    @Column(nullable = false)
    private String game;

    @Column
    private String brand;

    @Column
    private String attributes;

    /* ?============= Muchos a Muchos (ManyToMany) ============= */
    /* * Un categorias puede tener muchos producto */
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "product_has_categories",
            joinColumns =  @JoinColumn(name = "id_product"),
            inverseJoinColumns =  @JoinColumn(name = "id_categories")
    )
    private Set<Categories> categories;

    // Agregar un prodcuto con su categoria
    public void addCategory(Categories category) {
        this.categories.add(category);
        category.getProducts().add(this);
    }

    // Eliminar un prodcuto y su categoria
    public void removeCategory(Categories category) {
        this.categories.remove(category);
        category.getProducts().remove(this);
    }

}
