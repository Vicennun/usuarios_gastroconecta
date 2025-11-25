package com.fullstack.usuarios.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.fullstack.usuarios.dto.Receta;
import com.fullstack.usuarios.repository.RecetaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecetaService {
    
    private final RecetaRepository recetaRepository;

    public List<Receta> getAll() {
        return recetaRepository.findAll();
    }

    public Receta create(Receta receta) {
        // Por defecto confirmada o no, según tu lógica
        receta.setConfirmado(true); 
        return recetaRepository.save(receta);
    }

    public Receta getById(Long id) {
        return recetaRepository.findById(id).orElseThrow();
    }

    public Receta toggleLike(Long recetaId, Long userId) {
        Receta receta = getById(recetaId);
        if (receta.getLikes().contains(userId)) {
            receta.getLikes().remove(userId);
        } else {
            receta.getLikes().add(userId);
        }
        return recetaRepository.save(receta);
    }
}