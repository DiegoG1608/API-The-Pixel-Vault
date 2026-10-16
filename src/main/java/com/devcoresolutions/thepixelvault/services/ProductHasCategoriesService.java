package com.devcoresolutions.thepixelvault.services;

import com.devcoresolutions.thepixelvault.exceptions.ResourceNotFoundException;
import com.devcoresolutions.thepixelvault.models.Categories;
import com.devcoresolutions.thepixelvault.models.Product;
import com.devcoresolutions.thepixelvault.repository.CategoriesRepository;
import com.devcoresolutions.thepixelvault.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductHasCategoriesService {
    private final ProductRepository productRepository;
    private final CategoriesRepository categoriesRepository;

    /**
     * Se crea el registro para asignar una categoria al producto.
     * @param productId
     * @param categoryId
     */
    @Transactional
    public void addProductToCategory(Long productId, Long categoryId) {
        // Validar si exite el producto por su id
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ResourceNotFoundException("Product con el id " + productId + " no encontrado.")
        );
        // Validar si exite la categoria por su id
        Categories categories = categoriesRepository.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException("Categorie con el id " + categoryId + " no encontrado.")
        );

        product.addCategory(categories);
        productRepository.save(product);
    }

    /**
     * Se elimina el registro para asignar una categoria al producto.
     * @param productId
     * @param categoryId
     */
    @Transactional
    public void removeCategoriesFromProduct(Long productId, Long categoryId) {
        // Validar si exite el producto por su id
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ResourceNotFoundException("Product con el id " + productId + " no encontrado.")
        );
        // Validar si exite la categoria por su id
        Categories categories = categoriesRepository.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException("Categorie con el id " + categoryId + " no encontrado.")
        );
        product.removeCategory(categories);
        productRepository.save(product);
    }
}