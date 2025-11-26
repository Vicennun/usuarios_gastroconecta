package com.fullstack.usuarios.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.util.List;
import java.util.ArrayList;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @Email
    private String email;
    
    @NotBlank
    private String password;

    // --- NUEVO: Rol para admin ---
    private String rol = "user"; 

    @ElementCollection
    private List<Long> recetario = new ArrayList<>();

    @ElementCollection
    private List<Long> siguiendo = new ArrayList<>();

    @ElementCollection
    private List<Long> seguidores = new ArrayList<>();
}