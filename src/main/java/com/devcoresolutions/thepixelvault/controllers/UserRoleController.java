package com.devcoresolutions.thepixelvault.controllers;


import com.devcoresolutions.thepixelvault.dtos.UserRoleRequest;
import com.devcoresolutions.thepixelvault.models.UserRole;
import com.devcoresolutions.thepixelvault.services.UserRoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(path = "/api")
@AllArgsConstructor
public class UserRoleController {
    private final UserRoleService userRoleService;

    @GetMapping(path = "users/{usersId}/userRole")
    public ResponseEntity<UserRole> getRoleByUsersId(@PathVariable("usersId") Long usersId){
        UserRole userRole = userRoleService.getRoleByUsersId(usersId);
        return ResponseEntity.ok(userRole);
    }

    @PostMapping(path = "users/{usersId}/userRole")
    public ResponseEntity<UserRole> createRoleByUsersId(@PathVariable("usersId") Long usersId,
                                                        @RequestBody UserRoleRequest userRoleRequest){
        UserRole userRole = userRoleService.createUserRoleById(usersId, userRoleRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{userRoleId}")
                .buildAndExpand(userRole.getId())
                .toUri();
        return ResponseEntity.created(location).body(userRole);
    }

    @PutMapping(path = "userRole/{userRoleId}")
    public ResponseEntity<UserRole> updateRoleByUserRoleId (@PathVariable("userRoleId") Long userRoleId,
                                                        @RequestBody UserRoleRequest userRoleRequest){
        UserRole userRole = userRoleService.updateUserRoleById(userRoleId, userRoleRequest);
        return ResponseEntity.ok(userRole);
    }

    @DeleteMapping(path = "userRole/{userRoleId}")
    public ResponseEntity<UserRole> deleteRoleByUserRoleId (@PathVariable("userRoleId") Long userRoleId){
        UserRole userRole = userRoleService.deleteUserRoleById(userRoleId);
        return ResponseEntity.ok(userRole);
    }
}
