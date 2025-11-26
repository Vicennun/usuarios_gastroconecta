package com.fullstack.usuarios.dto;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import java.util.ArrayList;

@Entity
@Data
@Table(name = "recetas")
public class Receta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    
    @Column(length = 1000)
    private String descripcion;
    
    private String tiempoPreparacion;
    private Long autorId;
    private String autorNombre;
    
    // IMPORTANTE: LONGTEXT permite guardar la imagen en Base64
    @Column(columnDefinition = "LONGTEXT") 
    private String foto;
    
    private boolean confirmado;

    @ElementCollection
    private List<String> pasos = new ArrayList<>();

    @ElementCollection
    private List<String> etiquetasDieteticas = new ArrayList<>();

    @ElementCollection
    private List<Long> likes = new ArrayList<>();

    @ElementCollection
    private List<String> ingredientesSimples = new ArrayList<>();

    @ElementCollection
    private List<Comentario> comentarios = new ArrayList<>();

    // --- NUEVO: Lista de Ratings ---
    @ElementCollection
    private List<Rating> ratings = new ArrayList<>();
}