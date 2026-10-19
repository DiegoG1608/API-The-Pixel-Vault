package com.devcoresolutions.thepixelvault.models;

import com.devcoresolutions.thepixelvault.converters.UserAddressConverter;
import com.devcoresolutions.thepixelvault.dtos.UserAddress;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "users") //Aseguramos que el nombre de la tabla de la base de datos coincida con el de la clase
@IdClass(UsersId.class)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iduser") //aseguramos que coincida el nombre de la columna de la tabla en la base de datos con el nombre del atributo de la clas
    private Long id;

    @Id //Se coloca de igual forma la notacion @Id porque en la tabla de base de datos esta columna que es una llave forane, también forma parte de la llave primaria
    @ManyToOne
    @JoinColumn(name = "user_role_iduser_role", nullable = false)
    private UserRole userRoleIdUserRole;

    @Column(nullable = false)
    @Size(max = 100)
    private String email;

    @Column(nullable = false)
    @Size(max = 255)
    private String password;

    @Column(name = "user_name", nullable = false)
    @Size(max = 100)
    private String userName;

    @Column(name = "user_middle_name", nullable = false)
    @Size(max = 50)
    private String userMiddleName;

    @Column(name = "user_phone", nullable = false)
    @Size(max = 13)
    private String userPhone;

    @Column(name = "user_last_name_first", nullable = false)
    @Size(max = 100)
    private String userLastNameFirst;

    @Column(nullable = false, columnDefinition = "TINYINT DEFAULT 1")
    private Byte status;

    @Column(name = "user_last_name_second", nullable = false)
    @Size(max = 100)
    private String userLastNameSecond;

    // Configuración para el campo JSON con JPA estándar
    @Column(name = "user_address", columnDefinition = "json")
    @Convert(converter = UserAddressConverter.class)
    private UserAddress userAddress;

}
