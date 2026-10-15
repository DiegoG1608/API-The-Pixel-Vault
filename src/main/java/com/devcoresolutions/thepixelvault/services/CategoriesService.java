package com.devcoresolutions.thepixelvault.services;

import com.devcoresolutions.thepixelvault.models.Categories;
import com.devcoresolutions.thepixelvault.repository.CategoriesRepositorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriesService {

    @Autowired
    private CategoriesRepositorie categoriesRepositorie;

    public List<Categories> getAllCategories() {
        return categoriesRepositorie.findAll();
    }

    public Optional<Categories> getCategoryById(Long id) {
        return categoriesRepositorie.findById(id);
    }

    public Categories saveCategory(Categories category) {
        return categoriesRepositorie.save(category);
    }

    public Categories updateCategory(Long id, Categories categoryDetails) {
        return categoriesRepositorie.findById(id).map(category -> {
            category.setCategoryName(categoryDetails.getCategoryName());
            category.setCategoryDescription(categoryDetails.getCategoryDescription());
            return categoriesRepositorie.save(category);
        }).orElseGet(() -> {
            categoryDetails.setIdCategories(id);
            return categoriesRepositorie.save(categoryDetails);
        });
    }

    public void deleteCategory(Long id) {
        categoriesRepositorie.deleteById(id);
    }
}