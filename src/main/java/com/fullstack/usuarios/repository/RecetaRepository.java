package com.fullstack.usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fullstack.usuarios.dto.Receta;
import java.util.List;

public interface RecetaRepository extends JpaRepository<Receta, Long> {
    // Método para encontrar recetas por autor
    List<Receta> findByAutorId(Long autorId);
}