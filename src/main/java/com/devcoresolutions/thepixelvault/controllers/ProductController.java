package com.devcoresolutions.thepixelvault.controllers;

import com.devcoresolutions.thepixelvault.dtos.ProductRequest;
import com.devcoresolutions.thepixelvault.models.Product;
import com.devcoresolutions.thepixelvault.services.ProductHasCategoriesService;
import com.devcoresolutions.thepixelvault.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
@AllArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductHasCategoriesService productHasCategoriesService;

    // Obtener todos los productos
    @GetMapping(path = "/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    // Obtener todos los productos de cierta categoría
    @GetMapping(path = "/categories/{categoryId}/products")
    public ResponseEntity<List<Product>> getAllProductsByCategoryId(@PathVariable("categoryId") Long categoryId){
        List<Product> products = productService.getProductsByCategoryId(categoryId);
        return ResponseEntity.ok(products);
    }

    //Obtener un solo producto por id
    @GetMapping(path = "/products/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable("productId") Long productId){
        Product product = productService.getProductById(productId);
        return ResponseEntity.ok(product);
    }

    //Crear un producto a través de una categoría
    @PostMapping(path = "/categories/{categoryId}/products")
    public ResponseEntity<Product> createProductByCategoryId(@PathVariable("categoryId") Long categoryId, @RequestBody ProductRequest productRequest){
        Product product = productService.createProductByCategoryId(categoryId,productRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("{id}")
                .buildAndExpand(product.getId())
                .toUri();
        return ResponseEntity.created(location).body(product);
    }

    //Actualizar producto por id
    @PutMapping(path = "/products/{productId}")
    public ResponseEntity<Product> updateProductById(@PathVariable("productId") Long productId, @RequestBody ProductRequest productRequest){
        Product product = productService.updateProductById(productId,productRequest);
        return ResponseEntity.ok(product);
    }

    //Eliminar producto este aún no funciona, me imagino que porque tienes que borrar su relación con categorías a la hora de eliminarlo de la base de datos
    @DeleteMapping(path = "/products/{productId}")
    public ResponseEntity<Product> eliminateProductById(@PathVariable("productId") Long productId){
        Product product = productService.eliminateProductById(productId);
        return ResponseEntity.ok(product);
    }

    //Borrado lógico de productos
    @PostMapping(path = "/products/{productId}")
    public ResponseEntity<Product> deleteProductById(@PathVariable("productId") Long productId){
        Product product = productService.deleteProductById(productId);
        return ResponseEntity.ok(product);
    }



    /* ? Agregar categoria al producto */
    @PostMapping(path = "products/{productId}/categories/{categoryId}")
    public ResponseEntity<Void> addProductToCategory(
            @PathVariable("productId") Long productId,
            @PathVariable("categoryId") Long categoryId) {
        productHasCategoriesService.addProductToCategory(productId,categoryId);
        return  ResponseEntity.ok().build();
    }

    /* ? Eliminar categoria al producto */
    @DeleteMapping(path = "products/{productId}/categories/{categoryId}")
    public ResponseEntity<Void> removeCategorieFromProduct(
            @PathVariable("productId") Long productId,
            @PathVariable("categoryId") Long categoryId) {
        productHasCategoriesService.removeCategoriesFromProduct(productId, categoryId);
        return  ResponseEntity.ok().build();
    }
}
