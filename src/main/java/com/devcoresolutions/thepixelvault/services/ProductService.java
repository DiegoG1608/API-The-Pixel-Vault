package com.devcoresolutions.thepixelvault.services;

import com.devcoresolutions.thepixelvault.dtos.ProductRequest;
import com.devcoresolutions.thepixelvault.exceptions.ResourceNotFoundException;
import com.devcoresolutions.thepixelvault.models.Categories;
import com.devcoresolutions.thepixelvault.models.Product;
import com.devcoresolutions.thepixelvault.repository.ProductRepository;
import com.devcoresolutions.thepixelvault.repository.CategoriesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoriesRepository categoriesRepository;

    //Vamos a ver primero qué necesito hacer con la clase product
    //Get: Obtenerlos todos, por id y categoria?
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product getProductById(Long productId){
        return productRepository.findById(productId).orElseThrow(
                () -> new ResourceNotFoundException("Producto con id "+productId+" no encontrado")
        );
    }

    public List<Product> getProductsByCategoryId(Long categoryId){
        if(!categoriesRepository.existsById(categoryId)) throw new ResourceNotFoundException("Categoría con id "+categoryId+" no encontrada");
        return productRepository.findByCategoryId(categoryId);
    }
    //Post
    public Product createProductByCategoryId(Long categoryId, ProductRequest productRequest){
        Categories category = categoriesRepository.findById(categoryId).orElseThrow(
                () -> new ResourceNotFoundException("Categoría con id "+categoryId+" no fue encontrada")
        );
        Product product = new Product();
        product.setName(productRequest.name());
        product.setDescription(productRequest.description());
        product.setImage(productRequest.image());
        product.setPrice(productRequest.price());
        product.setRating(productRequest.rating());
        product.setStock(productRequest.stock());
        product.setGame(productRequest.game());
        product.setBrand(productRequest.brand());
        product.setAttributes(productRequest.attributes());

        productRepository.save(product);
        category.getProducts().add(product);
        categoriesRepository.save(category);
        return product;
    }
    //Put modificarlo por id
    public Product updateProductById(Long productId,ProductRequest productRequest){
        Product productToUpdate = getProductById(productId);

        if(productRequest.name() != null) productToUpdate.setName(productRequest.name());
        if(productRequest.description() != null) productToUpdate.setDescription(productRequest.description());
        if(productRequest.image() != null) productToUpdate.setImage(productRequest.image());
        if(productRequest.price() != null) productToUpdate.setPrice(productRequest.price());
        if(productRequest.rating() != null) productToUpdate.setRating(productRequest.rating());
        if(productRequest.stock() != null) productToUpdate.setStock(productRequest.stock());
        if(productRequest.game() != null) productToUpdate.setGame(productRequest.game());
        if(productRequest.brand() != null) productToUpdate.setBrand(productRequest.brand());
        if(productRequest.attributes() != null) productToUpdate.setAttributes(productRequest.attributes());
        return productRepository.save(productToUpdate);
    }

    //Delete? Es más cambiar el status que borrar el producto como tal
    public Product deleteProductById(Long productId){
        Byte deletedStatus = 0;
        Product product = getProductById(productId);
        product.setStatus(deletedStatus);
        return productRepository.save(product);
    }

    //Eliminate sí borra el producto,
    //se recomienda utilizarlo únicamente en caso de haber ingresado mal un producto
    //Para quitarlos de la tienda pero mantenerlos en el registro usen deleteProductById
    public Product eliminateProductById(Long productId){
        Product product = getProductById(productId);
        productRepository.delete(product);
        return product;
    }
}