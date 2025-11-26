package com.fullstack.usuarios.dto;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rating {
    private Long userId;
    private int score;
}