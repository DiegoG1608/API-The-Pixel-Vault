package com.devcoresolutions.thepixelvault.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersId implements Serializable {
    private Long id; //Es importante recalcar que el nombre de este atributo debe llamarse igual que el @Id de la entidad Users
    private Long userRoleIdUserRole; //Es importante también llamarse igual que la relación @Id de la entidad Users
}
