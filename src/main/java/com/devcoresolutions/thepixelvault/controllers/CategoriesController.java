package com.devcoresolutions.thepixelvault.controllers;

import com.devcoresolutions.thepixelvault.models.Categories;
import com.devcoresolutions.thepixelvault.services.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "*")
public class CategoriesController {

    @Autowired
    private CategoriesService categoriesService;

    @GetMapping //(libre)
    public ResponseEntity<List<Categories>> getAllCategories() {
        List<Categories> categories = categoriesService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}") //(libre)
    public ResponseEntity<Categories> getCategoryById(@PathVariable Long id) {
        Optional<Categories> category = categoriesService.getCategoryById(id);
        return category.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<Categories> createCategory(@RequestBody Categories category) {
        Categories newCategory = categoriesService.saveCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Categories> updateCategory(@PathVariable Long id, @RequestBody Categories categoryDetails) {
        Categories updatedCategory = categoriesService.updateCategory(id, categoryDetails);
        return ResponseEntity.ok(updatedCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        if (categoriesService.getCategoryById(id).isPresent()) {
            categoriesService.deleteCategory(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}