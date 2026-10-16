package com.devcoresolutions.thepixelvault.services;

import com.devcoresolutions.thepixelvault.models.Categories;
import com.devcoresolutions.thepixelvault.repository.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriesService {

    @Autowired
    private CategoriesRepository categoriesRepository;

    public List<Categories> getAllCategories() {
        return categoriesRepository.findAll();
    }

    public Optional<Categories> getCategoryById(Long id) {
        return categoriesRepository.findById(id);
    }

    public Categories saveCategory(Categories category) {
        return categoriesRepository.save(category);
    }

    public Categories updateCategory(Long id, Categories categoryDetails) {
        return categoriesRepository.findById(id).map(category -> {
            category.setCategoryName(categoryDetails.getCategoryName());
            category.setCategoryDescription(categoryDetails.getCategoryDescription());
            return categoriesRepository.save(category);
        }).orElseGet(() -> {
            categoryDetails.setIdCategories(id);
            return categoriesRepository.save(categoryDetails);
        });
    }

    public void deleteCategory(Long id) {
        categoriesRepository.deleteById(id);
    }
}