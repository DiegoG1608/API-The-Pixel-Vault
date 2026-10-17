package com.devcoresolutions.thepixelvault.models;

import com.devcoresolutions.thepixelvault.converters.UserAddressConverter;
import com.devcoresolutions.thepixelvault.dtos.UserAddress;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(max = 100)
    private String email;

    @Column(nullable = false)
    @Size(max = 255)
    private String password;

    @Column(nullable = false)
    @Size(max = 100)
    private String userName;

    @Column(nullable = false)
    @Size(max = 50)
    private String userMiddleName;

    @Column(nullable = false)
    @Size(max = 13)
    private String userPhone;

    @Column(nullable = false)
    @Size(max = 100)
    private String userLastNameFirst;

    @Column(nullable = false)
    @Size(max = 1)
    private Byte status;

    @ManyToOne
    @JoinColumn(name = "user_role_iduser_role", nullable = false)
    private UserRole userRoleIdUserRole;

    @Column(nullable = false)
    private String userLastNameSecond;

    // Configuración para el campo JSON con JPA estándar
    @Column(name = "user_address", columnDefinition = "json")
    @Convert(converter = UserAddressConverter.class)
    private UserAddress userAddress;

}
