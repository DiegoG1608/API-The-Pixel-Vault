package com.devcoresolutions.thepixelvault.controllers;

import com.devcoresolutions.thepixelvault.dtos.RegisterRequest;
import com.devcoresolutions.thepixelvault.dtos.UsersRequest;
import com.devcoresolutions.thepixelvault.models.Users;
import com.devcoresolutions.thepixelvault.services.UsersService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/api/users")
@AllArgsConstructor
public class UsersController {
    private final UsersService usersService;

    // URL Ejemplo: http://localhost:8080/api/users
    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers() {
        List<Users> usersList = usersService.getAllUsers();
        return ResponseEntity.ok(usersList);
    }

    // URL Ejemplo: http://localhost:8080/api/users/1
    @GetMapping(path = "/{id}")
    public ResponseEntity<Users> getUsersById(@PathVariable("id") Long id) {
        Users user = usersService.getUsersById(id);
        return ResponseEntity.ok(user);
    }

    // URL Ejemplo: http://localhost:8080/api/users/email/juan@mail.com
    @GetMapping(path = "/email/{email}")
    public ResponseEntity<Users> getUsesrByEmail(@PathVariable("email") String email){
        Users user = usersService.getUserByEmail(email);
        return ResponseEntity.ok(user);
    }

    // URL Ejemplo: http://localhost:8080/api/users
    @PostMapping
    public ResponseEntity<Users> createUsers(@Valid @RequestBody UsersRequest usersRequest) {
        Users users = usersService.createUsers(usersRequest);

        // Ajustamos la locación para incluir tanto el id del usuario como el del rol
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(users.getId())
                .toUri();
        return ResponseEntity.created(location).body(users);
    }

    // URL Ejemplo: http://localhost:8080/api/users/1
    @PutMapping(path = "/{id}")
    public ResponseEntity<Users> updateUsersById(
            @PathVariable("id") Long id,
            @RequestBody UsersRequest usersRequest) {

        Users users = usersService.updateUsers(id, usersRequest);
        return ResponseEntity.ok(users);
    }

    // URL Ejemplo: http://localhost:8080/api/users/part/1
    @PutMapping(path = "/part/{id}")
    public ResponseEntity<Users> parcialUpdateUsersById(
            @PathVariable("id") Long id,
            @RequestBody RegisterRequest registerRequest) {

        Users users = usersService.updateUsers(id, registerRequest);
        return ResponseEntity.ok(users);
    }

    // URL Ejemplo: http://localhost:8080/api/users/1
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteUsersById(@PathVariable("id") Long id) {
        usersService.deleteUsersById(id);
        return ResponseEntity.noContent().build(); // Buena práctica: Responder 204 No Content al borrar
    }


}
