package com.devcoresolutions.thepixelvault.repository;

import com.devcoresolutions.thepixelvault.models.Users;
import com.devcoresolutions.thepixelvault.models.UsersId;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * En este caso indicamos que el tipo de llave primaria es de Tipo UsersId que es la clase extra que se agregó
 * por el tema de la llave primaria compuesta declarada en la tabla de la base de datos.
*/
public interface UsersRepository extends JpaRepository<Users, UsersId> {
    Optional<Users> findByEmail(String email);
}
