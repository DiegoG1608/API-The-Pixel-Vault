package com.devcoresolutions.thepixelvault.config;

import com.devcoresolutions.thepixelvault.models.UserRole;
import com.devcoresolutions.thepixelvault.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {
    private final UserRoleRepository userRoleRepository;

    @Override
    public void run(String... args) {
        for (String name : List.of("ADMIN", "CLIENTE")) {
            if (userRoleRepository.findByRoleName(name).isEmpty()) {
                UserRole role = new UserRole();
                role.setRoleName(name);
                userRoleRepository.save(role);
            }
        }
    }
}