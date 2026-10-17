package com.devcoresolutions.thepixelvault.controllers;

import com.devcoresolutions.thepixelvault.dtos.UserRoleRequest;
import com.devcoresolutions.thepixelvault.models.UserRole;
import com.devcoresolutions.thepixelvault.services.UserRoleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping(path = "/api/userRole")
@AllArgsConstructor
public class UserRoleController {
    private final UserRoleService userRoleService;

    // Obtener rol con id
    @GetMapping(path = "/{userRoleId}")
    public ResponseEntity<UserRole> getRoleByUserRoleId(@PathVariable("userRoleId") Long userRoleId){
        UserRole userRole = userRoleService.getUserRoleById(userRoleId);
        return ResponseEntity.ok(userRole);
    }

    // Obtener todos los roles existentes
    @GetMapping
    public ResponseEntity<List<UserRole>> getAllUserRoles(){
        List<UserRole> roles = userRoleService.getAllUserRoles();
        return ResponseEntity.ok(roles);
    }

    // Crear rol
    @PostMapping
    public ResponseEntity<UserRole> createUserRole(@Valid @RequestBody UserRoleRequest userRoleRequest){
        UserRole userRole = userRoleService.createUserRole(userRoleRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userRole.getId())
                .toUri();
        return ResponseEntity.created(location).body(userRole);
    }

    // Modificar rol
    @PutMapping(path = "/{userRoleId}")
    public ResponseEntity<UserRole> updateRoleByUserRoleId (@PathVariable("userRoleId") Long userRoleId,
                                                            @Valid @RequestBody UserRoleRequest userRoleRequest){
        UserRole userRole = userRoleService.updateUserRoleById(userRoleId, userRoleRequest);
        return ResponseEntity.ok(userRole);
    }

    // Eliminar rol
    @DeleteMapping(path = "/{userRoleId}")
    public ResponseEntity<UserRole> deleteRoleByUserRoleId (@PathVariable("userRoleId") Long userRoleId){
        UserRole userRole = userRoleService.deleteUserRoleById(userRoleId);
        return ResponseEntity.ok(userRole);
    }

    // Captura PUT sin ID
    @PutMapping
    public void updateRoleMissingId() {
        throw new IllegalArgumentException("El identificador del rol es requerido en la URL para poder editarlo.");
    }

    // Captura DELETE sin ID
    @DeleteMapping
    public void deleteRoleMissingId() {
        throw new IllegalArgumentException("El identificador del rol es requerido en la URL para poder eliminarlo.");
    }

    // Captura el formato inválido
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ProblemDetail handleLocalTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String valorEnviado = ex.getValue() != null ? ex.getValue().toString() : "";

        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                String.format("El formato del identificador de rol no es válido. Se recibió '%s' pero debe ser un número entero.", valorEnviado)
        );

        problem.setTitle("Formato de Rol Inválido");
        problem.setType(URI.create("http://localhost:8080/errors/invalid-role-id"));
        problem.setProperty("timestamp", Instant.now());

        return problem;
    }
}
