package com.devcoresolutions.thepixelvault.repository;

import com.devcoresolutions.thepixelvault.models.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepositorie extends JpaRepository<Categories, Long> {
}