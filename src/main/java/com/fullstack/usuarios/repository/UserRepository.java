package com.fullstack.usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fullstack.usuarios.dto.User;

public interface UserRepository extends JpaRepository<User, Long> {

    
}