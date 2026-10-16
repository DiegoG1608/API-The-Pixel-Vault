package com.devcoresolutions.thepixelvault.services;

import com.devcoresolutions.thepixelvault.dtos.UsersRequest;
import com.devcoresolutions.thepixelvault.exceptions.ResourceNotFoundException;
import com.devcoresolutions.thepixelvault.models.Users;
import com.devcoresolutions.thepixelvault.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersService {

    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public List<Users> getAllUsers(){
        return usersRepository.findAll();
    }

    public Users getUsersById(Long id){
        return usersRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Usuario con el id " + id + " no encontrado")
        );
    }

    public Users createUsers(UsersRequest newUser){
        Users user = new Users();
        user.setEmail(newUser.email());
        user.setPassword(newUser.password());
        user.setUserName(newUser.userName());
        user.setUserMiddleName(newUser.userMiddleName());
        user.setUserPhone(newUser.userPhone());
        user.setUserLastNameFirst(newUser.userLastNameFirst());
        user.setStatus(newUser.status());
        user.setUserLastNameSecond(newUser.userLastNameSecond());
        user.setUserAddress(newUser.userAddress());
        return usersRepository.save(user);
    }

    public Users updateUsers(Long id, UsersRequest updateUser){
        Users oldUser = usersRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Usuario con el " + id + " no encontrado")
        );
        if (updateUser.email() != null) oldUser.setEmail(updateUser.email());
        if (updateUser.password() != null) oldUser.setPassword(updateUser.password());
        if (updateUser.userName() != null) oldUser.setUserName(updateUser.userName());
        if (updateUser.userMiddleName() != null) oldUser.setUserMiddleName(updateUser.userMiddleName());
        if (updateUser.userPhone() != null) oldUser.setUserPhone(updateUser.userPhone());
        if (updateUser.userLastNameFirst() != null) oldUser.setUserLastNameFirst(updateUser.userLastNameFirst());
        if (updateUser.status() != null) oldUser.setStatus(updateUser.status());
        if (updateUser.userLastNameSecond() != null) oldUser.setUserLastNameSecond(updateUser.userLastNameSecond());
        if (updateUser.userAddress() != null) oldUser.setUserAddress(updateUser.userAddress());
        return usersRepository.save(oldUser);
    }

    public Users deleteUsersById(Long id){
        Users user = usersRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Usuario con el id " + id + " no encontrado")
        );
        usersRepository.delete(user);
        return user;
    }
}
