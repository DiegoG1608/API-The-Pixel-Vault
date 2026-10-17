package com.devcoresolutions.thepixelvault.repository;

import com.devcoresolutions.thepixelvault.models.Categories;
import com.devcoresolutions.thepixelvault.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    //Propuesta de método para encontrar productos por el nombre y
    // luego usar un método que no permita ingresar nombres repetidos,
    //  pero hay que preguntarle a Josue de preferencia
    Product findByName(String productName);

    List<Product> findByCategories(Categories categories);
}