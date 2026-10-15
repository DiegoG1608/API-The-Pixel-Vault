package com.devcoresolutions.thepixelvault.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    Date orderDate;

    @Column(nullable = false)
    Double total;

    @ManyToOne
    @JoinColumn(name = "user_iduser", nullable = false)
    @JsonIgnore
    private Users users;
}

