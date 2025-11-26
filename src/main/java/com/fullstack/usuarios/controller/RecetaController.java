package com.fullstack.usuarios.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fullstack.usuarios.dto.Receta;
import com.fullstack.usuarios.dto.Comentario;
import com.fullstack.usuarios.dto.Rating;
import com.fullstack.usuarios.service.RecetaService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/recetas")
@CrossOrigin("*")
@RequiredArgsConstructor
public class RecetaController {
    
    private final RecetaService recetaService;

    @GetMapping
    public List<Receta> getAll() {
        return recetaService.getAll();
    }

    @PostMapping
    public ResponseEntity<Receta> create(@RequestBody Receta receta) {
        return ResponseEntity.ok(recetaService.create(receta));
    }

    @PostMapping("/{id}/like")
    public ResponseEntity<Receta> toggleLike(@PathVariable Long id, @RequestParam Long userId) {
        return ResponseEntity.ok(recetaService.toggleLike(id, userId));
    }

    @PostMapping("/{id}/comentar")
    public ResponseEntity<Receta> comentar(@PathVariable Long id, @RequestBody Comentario comentario) {
        Receta receta = recetaService.getById(id);
        receta.getComentarios().add(0, comentario); 
        return ResponseEntity.ok(recetaService.create(receta)); 
    }

    // --- NUEVOS ENDPOINTS ---

    @PostMapping("/{id}/rate")
    public ResponseEntity<Receta> rate(@PathVariable Long id, @RequestBody Rating rating) {
        Receta receta = recetaService.getById(id);
        // Si ya votó, actualizamos su voto. Si no, lo agregamos.
        receta.getRatings().removeIf(r -> r.getUserId().equals(rating.getUserId()));
        receta.getRatings().add(rating);
        return ResponseEntity.ok(recetaService.create(receta));
    }

    @PutMapping("/{id}/confirmar")
    public ResponseEntity<Receta> confirmar(@PathVariable Long id) {
        Receta receta = recetaService.getById(id);
        receta.setConfirmado(true);
        return ResponseEntity.ok(recetaService.create(receta));
    }
}