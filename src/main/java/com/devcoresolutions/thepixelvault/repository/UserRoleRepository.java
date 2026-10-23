package com.devcoresolutions.thepixelvault.repository;

import com.devcoresolutions.thepixelvault.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


//ELIMINAR ESTA CLASE, SE CREÓ UNICAMENTE PARA LAS PRUEBAS
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    Optional<UserRole> findByRoleName(String roleName);
}
