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
    private UsersRepository usersRepository;

    public UserRole getRoleByUsersId(Long usersId){
        Users users = usersRepository.findById(usersId).orElseThrow(
                () -> new ResourceNotFoundException("Usuario con el id " + usersId + " no encontrado.")
        );
        return users.getUserRole();
    }

    public UserRole getUserRoleById(Long userRoleId){
        return userRoleRepository.findById(userRoleId).orElseThrow(
                () -> new ResourceNotFoundException("Rol del usuario con el id " + userRoleId + " no encontrado.")
        );
    }

    public UserRole createUserRoleById(Long usersId, UserRoleRequest userRoleRequest){
        Users users = usersRepository.findById(usersId).orElseThrow(
                () -> new ResourceNotFoundException("Usuario con el id " + usersId + " no encontrado.")
        );

        UserRole userRole = new UserRole();
        userRole.setRoleName(userRoleRequest.roleName());
        userRole = userRoleRepository.save(userRole);
        users.setUserRole(userRole);
        usersRepository.save(users);
        return userRole;
    }

    public UserRole updateUserRoleById(Long userRoleId, UserRoleRequest userRoleRequest){
        UserRole userRoleStored = getUserRoleById(userRoleId);
        if(userRoleRequest.roleName() != null) userRoleStored.setRoleName(userRoleRequest.roleName());
        return userRoleRepository.save(userRoleStored);
    }

    public UserRole deleteUserRoleById(Long userRoleId){
        UserRole userRole = getUserRoleById(userRoleId);
        userRoleRepository.delete(userRole);
        return userRole;
    }
}
