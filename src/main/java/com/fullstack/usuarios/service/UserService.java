package com.fullstack.usuarios.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.fullstack.usuarios.dto.User;
import com.fullstack.usuarios.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public Optional<User> login(String email, String password) {
        return userRepository.findByEmail(email)
                .filter(u -> u.getPassword() != null && u.getPassword().equals(password));
    }

    
   public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    public User create(User user) {
        return userRepository.save(user);
    }
    
    public User update(Long id, User user) {
        User dbUser = getById(id);
        dbUser.setName(user.getName());
        dbUser.setEmail(user.getEmail());
        return userRepository.save(dbUser);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
