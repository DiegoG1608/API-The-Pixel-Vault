package com.devcoresolutions.thepixelvault.services;

import com.devcoresolutions.thepixelvault.dtos.UserRoleRequest;
import com.devcoresolutions.thepixelvault.exceptions.ResourceNotFoundException;
import com.devcoresolutions.thepixelvault.models.UserRole;
import com.devcoresolutions.thepixelvault.repository.UserRoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserRoleService {
    private UserRoleRepository userRoleRepository;

    // Obtener rol con id
    public UserRole getUserRoleById(Long userRoleId){
        return userRoleRepository.findById(userRoleId).orElseThrow(
                () -> new ResourceNotFoundException("Rol con el id " + userRoleId + " no encontrado.")
        );
    }

    // Obtener todos los roles existentes
    public List<UserRole> getAllUserRoles() {
        return userRoleRepository.findAll();
    }

    // Crear rol
    public UserRole createUserRole(UserRoleRequest userRoleRequest){
        UserRole userRole = new UserRole();
        userRole.setRoleName(userRoleRequest.roleName());
        return userRoleRepository.save(userRole);
    }

    // Modificar rol
    public UserRole updateUserRoleById(Long userRoleId, UserRoleRequest userRoleRequest){
        UserRole userRoleStored = getUserRoleById(userRoleId);
        if(userRoleRequest.roleName() != null) userRoleStored.setRoleName(userRoleRequest.roleName());
        return userRoleRepository.save(userRoleStored);
    }

    // Eliminar rol
    public UserRole deleteUserRoleById(Long userRoleId){
        UserRole userRole = getUserRoleById(userRoleId);
        userRoleRepository.delete(userRole);
        return userRole;
    }
}
