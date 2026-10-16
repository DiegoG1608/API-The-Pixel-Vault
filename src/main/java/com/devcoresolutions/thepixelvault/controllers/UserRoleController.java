package com.devcoresolutions.thepixelvault.controllers;

import com.devcoresolutions.thepixelvault.dtos.UserRoleRequest;
import com.devcoresolutions.thepixelvault.models.UserRole;
import com.devcoresolutions.thepixelvault.services.UserRoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/api/userRole")
@AllArgsConstructor
public class UserRoleController {
    private final UserRoleService userRoleService;

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

    @PostMapping
    public ResponseEntity<UserRole> createUserRole(@RequestBody UserRoleRequest userRoleRequest){
        UserRole userRole = userRoleService.createUserRole(userRoleRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userRole.getId())
                .toUri();
        return ResponseEntity.created(location).body(userRole);
    }

    @PutMapping(path = "/{userRoleId}")
    public ResponseEntity<UserRole> updateRoleByUserRoleId (@PathVariable("userRoleId") Long userRoleId,
                                                        @RequestBody UserRoleRequest userRoleRequest){
        UserRole userRole = userRoleService.updateUserRoleById(userRoleId, userRoleRequest);
        return ResponseEntity.ok(userRole);
    }

    @DeleteMapping(path = "/{userRoleId}")
    public ResponseEntity<UserRole> deleteRoleByUserRoleId (@PathVariable("userRoleId") Long userRoleId){
        UserRole userRole = userRoleService.deleteUserRoleById(userRoleId);
        return ResponseEntity.ok(userRole);
    }
}
