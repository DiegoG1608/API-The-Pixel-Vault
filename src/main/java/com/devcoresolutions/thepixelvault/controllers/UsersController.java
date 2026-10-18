package com.devcoresolutions.thepixelvault.controllers;

import com.devcoresolutions.thepixelvault.dtos.UsersRequest;
import com.devcoresolutions.thepixelvault.models.Users;
import com.devcoresolutions.thepixelvault.models.UsersId;
import com.devcoresolutions.thepixelvault.services.UsersService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/api/users") // http://localhost:8080/api/users
@AllArgsConstructor
public class UsersController {
    private final UsersService usersService;

    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers() {
        List<Users> usersList = usersService.getAllUsers();
        return ResponseEntity.ok(usersList);
    }

    // URL Ejemplo: http://localhost:8080/api/users/1/2
    @GetMapping(path = "/{id}/{roleId}")
    public ResponseEntity<Users> getUsersById(@PathVariable("id") Long id, @PathVariable("roleId") Long roleId) {
        UsersId usersId = new UsersId(id, roleId); // Construimos el objeto ID compuesto
        Users user = usersService.getUsersById(usersId);
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<Users> createUsers(@Valid @RequestBody UsersRequest usersRequest) {
        Users users = usersService.createUsers(usersRequest);

        // Ajustamos la locación para incluir tanto el id del usuario como el del rol
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}/{roleId}")
                .buildAndExpand(users.getId(), users.getUserRoleIdUserRole().getId())
                .toUri();
        return ResponseEntity.created(location).body(users);
    }

    // URL Ejemplo: http://localhost:8080/api/users/1/2
    @DeleteMapping(path = "/{id}/{roleId}")
    public ResponseEntity<Void> deleteUsersById(@PathVariable("id") Long id, @PathVariable("roleId") Long roleId) {
        UsersId usersId = new UsersId(id, roleId);
        usersService.deleteUsersById(usersId);
        return ResponseEntity.noContent().build(); // Buena práctica: Responder 204 No Content al borrar
    }

    // URL Ejemplo: http://localhost:8080/api/users/1/2
    @PutMapping(path = "/{id}/{roleId}") // <--- CORREGIDO: Añadidos los path variables y los corchetes
    public ResponseEntity<Users> updateUsersById(
            @PathVariable("id") Long id,
            @PathVariable("roleId") Long roleId,
            @RequestBody UsersRequest usersRequest) {

        UsersId usersId = new UsersId(id, roleId);
        Users users = usersService.updateUsers(usersId, usersRequest);
        return ResponseEntity.ok(users);
    }
}
