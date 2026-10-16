package com.devcoresolutions.thepixelvault.controllers;

import com.devcoresolutions.thepixelvault.dtos.UsersRequest;
import com.devcoresolutions.thepixelvault.models.Users;
import com.devcoresolutions.thepixelvault.services.UsersService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/api/user") //http://localhost:8080/api/users
@AllArgsConstructor
public class UsersController {
    private final UsersService usersService;

    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers(){
        List<Users> usersList = usersService.getAllUsers();
        return ResponseEntity.ok(usersList);
    }

    @GetMapping(path = "{usersId}")//http://localhost:8080/api/users/id
    public ResponseEntity<Users> getUsersById(@PathVariable("usersId") Long id){
        Users user = usersService.getUsersById(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public  ResponseEntity<Users> createUsers(@Valid @RequestBody UsersRequest usersRequest){
        Users users = usersService.createUsers(usersRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(users.getId())
                .toUri();
        return ResponseEntity.created(location).body(users);
    }

    @DeleteMapping(path = "{usersId}") //http://localhost:8080/api/users/id
    public  ResponseEntity<Users> deleteUsersById(@PathVariable("usersId") Long id){
        Users users = usersService.deleteUsersById(id);
        return ResponseEntity.ok(users);
    }

    @PutMapping(path = "usersId")
    public ResponseEntity<Users> updateUsersById //http://localhost:8080/api/users/id
    (@PathVariable("usersId") Long id, @RequestBody UsersRequest usersRequest){
        Users users = usersService.updateUsers(id, usersRequest);
        return ResponseEntity.ok(users);
    }
}
