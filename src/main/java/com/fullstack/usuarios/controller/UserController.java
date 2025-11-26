package com.fullstack.usuarios.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import java.util.Optional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;

import com.fullstack.usuarios.dto.User;
import com.fullstack.usuarios.dto.LoginRequest;
import com.fullstack.usuarios.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin("*")
@RequiredArgsConstructor
public class UserController {
    
    private final UserService userService;

    @GetMapping
    public List<User> getAll(){
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        return userService.getById(id);
    }


    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) {
        return ResponseEntity.ok(userService.create(user));
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginRequest request) {
        Optional<User> maybeUser = userService.login(request.getEmail(), request.getPassword());
        return maybeUser.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) {
        return ResponseEntity.ok(userService.update(id, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{userId}/guardar/{recetaId}")
    public ResponseEntity<User> toggleGuardar(@PathVariable Long userId, @PathVariable Long recetaId) {
        User usuario = userService.getById(userId);
        if (usuario.getRecetario().contains(recetaId)) {
            usuario.getRecetario().remove(recetaId);
        } else {
            usuario.getRecetario().add(recetaId);
        }
        return ResponseEntity.ok(userService.create(usuario)); // Guardamos
    }

    @PostMapping("/{userId}/seguir/{targetId}")
    public ResponseEntity<User> toggleSeguir(@PathVariable Long userId, @PathVariable Long targetId) {
        User usuarioActual = userService.getById(userId);
        User usuarioObjetivo = userService.getById(targetId);

        if (usuarioActual.getSiguiendo().contains(targetId)) {
            usuarioActual.getSiguiendo().remove(targetId);
            usuarioObjetivo.getSeguidores().remove(userId);
        } else {
            usuarioActual.getSiguiendo().add(targetId);
            usuarioObjetivo.getSeguidores().add(userId);
        }
        
        userService.create(usuarioObjetivo); // Guardamos al otro
        return ResponseEntity.ok(userService.create(usuarioActual)); // Guardamos al actual
    }
}
