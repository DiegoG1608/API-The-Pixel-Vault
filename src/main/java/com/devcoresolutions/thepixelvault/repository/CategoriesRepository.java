package com.devcoresolutions.thepixelvault.repository;

import com.devcoresolutions.thepixelvault.models.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Long> {
    Optional<Categories> findByCategoryName(String categoryName);
}