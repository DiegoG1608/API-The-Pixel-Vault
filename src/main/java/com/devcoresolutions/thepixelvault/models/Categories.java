package com.devcoresolutions.thepixelvault.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "categories")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Categories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_categories")
    private Long idCategories;

    @Column(name = "category_name", nullable = false, length = 100)
    private String categoryName;

    @Column(name = "category_description", length = 255)
    private String categoryDescription;

    /* ?============= Muchos a Muchos (ManyToMany) ============= */
    /* * Un producto puede tener muchas categorias */
    @ManyToMany(mappedBy = "categories")
    @JsonIgnore
    private Set<Product> products;


}