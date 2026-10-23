package com.devcoresolutions.thepixelvault.config;

import com.devcoresolutions.thepixelvault.models.Categories;
import com.devcoresolutions.thepixelvault.models.UserRole;
import com.devcoresolutions.thepixelvault.repository.CategoriesRepository;
import com.devcoresolutions.thepixelvault.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final UserRoleRepository userRoleRepository;
    private final CategoriesRepository categoriesRepository;

    @Override
    public void run(String... args) {
        for (String name : List.of("ADMIN", "CLIENTE")) {
            if (userRoleRepository.findByRoleName(name).isEmpty()) {
                UserRole role = new UserRole();
                role.setRoleName(name);
                userRoleRepository.save(role);
            }
        }
        for (String categoryName : List.of("Videojuegos","Merch","Físico","Digital","Edición Especial","CDs y Viniles","Peluches","Arte y Pósters","Accesorios","Ropa y Calzado")){
            if (categoriesRepository.findByCategoryName(categoryName).isEmpty()){
                Categories categories = new Categories();
                categories.setCategoryName(categoryName);
                categoriesRepository.save(categories);
            }
        }

    }
}