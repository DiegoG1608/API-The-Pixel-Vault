package com.devcoresolutions.thepixelvault.services;

import com.devcoresolutions.thepixelvault.dtos.UsersRequest;
import com.devcoresolutions.thepixelvault.exceptions.ResourceNotFoundException;
import com.devcoresolutions.thepixelvault.models.Users;
import com.devcoresolutions.thepixelvault.models.UserRole;
import com.devcoresolutions.thepixelvault.repository.UsersRepository;
import com.devcoresolutions.thepixelvault.repository.UserRoleRepository; // <--- Asegúrate de tener este repositorio creado
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor // <--- Simplificamos los constructores con Lombok
public class UsersService {

    private final UsersRepository usersRepository;
    private final UserRoleRepository userRoleRepository; // <--- Inyectado para validar el rol en la FK compuesto
    private final PasswordEncoder passwordEncoder;       // <--- Inyectado para proteger contraseñas

    public List<Users> getAllUsers(){
        return usersRepository.findAll();
    }

    public Users getUsersById(Long id){
        return usersRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Usuario con el id " + id + " no encontrado")
        );
    }

    public Users getUserByEmail(String email){
        return usersRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("Usuario con el email " + email + " no encontrado")
        );
    }

    public Users createUsers(UsersRequest newUser){
        // 1. Validar y buscar el Rol correspondiente antes de proceder
        // (Asumiendo que tu UsersRequest tiene un método .roleId() o similar)
        UserRole role = userRoleRepository.findById(newUser.roleId()).orElseThrow(
                () -> new ResourceNotFoundException("El rol con id " + newUser.roleId() + " no existe.")
        );

        Users user = new Users();
        user.setEmail(newUser.email());

        // 2. Encriptar contraseña por seguridad
        user.setPassword(passwordEncoder.encode(newUser.password()));

        user.setUserName(newUser.userName());
        user.setUserMiddleName(newUser.userMiddleName());
        user.setUserPhone(newUser.userPhone());
        user.setUserLastNameFirst(newUser.userLastNameFirst());
        user.setStatus(newUser.status());
        user.setUserLastNameSecond(newUser.userLastNameSecond());
        user.setUserAddress(newUser.userAddress());

        // 3. Asignar obligatoriamente la parte de la clave compuesta del rol
        user.setIdUserRole(role);

        return usersRepository.save(user);
    }

    public Users updateUsers(Long id, UsersRequest updateUser){
        Users oldUser = usersRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Usuario con el id " + id + " no encontrado")
        );

        if (updateUser.email() != null) oldUser.setEmail(updateUser.email());

        // Si cambia contraseña, volver a encriptar
        if (updateUser.password() != null) oldUser.setPassword(passwordEncoder.encode(updateUser.password()));

        if (updateUser.userName() != null) oldUser.setUserName(updateUser.userName());
        if (updateUser.userMiddleName() != null) oldUser.setUserMiddleName(updateUser.userMiddleName());
        if (updateUser.userPhone() != null) oldUser.setUserPhone(updateUser.userPhone());
        if (updateUser.userLastNameFirst() != null) oldUser.setUserLastNameFirst(updateUser.userLastNameFirst());
        if (updateUser.status() != null) oldUser.setStatus(updateUser.status());
        if (updateUser.userLastNameSecond() != null) oldUser.setUserLastNameSecond(updateUser.userLastNameSecond());
        if (updateUser.userAddress() != null) oldUser.setUserAddress(updateUser.userAddress());

        // Si el diseño permite cambiar de rol en la clave compuesta, tendrías que manejarlo aquí.
        // Pero comúnmente una clave primaria compuesta no debe modificarse directamente.

        return usersRepository.save(oldUser);
    }

    public void deleteUsersById(Long id){ // Cambiado a void ya que el controlador maneja NoContent
        Users user = usersRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Usuario con el id " + id + " no encontrado")
        );
        usersRepository.delete(user);
    }
}
