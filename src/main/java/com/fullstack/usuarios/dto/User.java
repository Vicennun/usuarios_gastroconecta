package com.fullstack.usuarios.dto;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    private String name;

    @Email(message = "Debe ser un correo válido")
    private String email;
    
    @NotBlank(message = "La contraseña no puede estar vacía")
    private String password;
    
    @ElementCollection
    private List<Long> recetario = new ArrayList<>(); // IDs de recetas guardadas

    @ElementCollection
    private List<Long> siguiendo = new ArrayList<>(); // IDs de usuarios seguidos

    @ElementCollection
    private List<Long> seguidores = new ArrayList<>(); // IDs de quienes me siguen

}
