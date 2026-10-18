package com.devcoresolutions.thepixelvault.repository;

import com.devcoresolutions.thepixelvault.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


//ELIMINAR ESTA CLASE, SE CREÓ UNICAMENTE PARA LAS PRUEBAS
@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    // Aquí puedes heredar con tranquilidad usando 'Long' porque la PK de UserRole es simple (iduser_role)
}
