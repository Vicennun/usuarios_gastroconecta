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
    
    @Column(length = 1000)
    private String foto;
    
    private boolean confirmado;

    // Listas simples guardadas en tablas auxiliares automáticas
    @ElementCollection
    private List<String> pasos = new ArrayList<>();

    @ElementCollection
    private List<String> etiquetasDieteticas = new ArrayList<>();

    @ElementCollection
    private List<Long> likes = new ArrayList<>();

    // Para simplificar ingredientes (guardaremos el string completo por ahora "Harina, 1 taza")
    // En el futuro puedes crear una entidad Ingrediente separada.
    @ElementCollection
    private List<String> ingredientesSimples = new ArrayList<>();
}