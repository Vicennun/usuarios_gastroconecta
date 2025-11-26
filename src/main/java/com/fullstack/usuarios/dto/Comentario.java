package com.fullstack.usuarios.dto;

import jakarta.persistence.Embeddable;
import lombok.Data;
import java.time.LocalDateTime;

@Embeddable
@Data
public class Comentario {
    private Long autorId;
    private String autorNombre;
    private String texto;
    private String fecha;

    public Comentario() {
        this.fecha = LocalDateTime.now().toString();
    }

    public Comentario(Long autorId, String autorNombre, String texto) {
        this.autorId = autorId;
        this.autorNombre = autorNombre;
        this.texto = texto;
        this.fecha = LocalDateTime.now().toString();
    }
}