package com.salesianostriana.dam.proyectoapirecetas.Dto;

import com.salesianostriana.dam.proyectoapirecetas.Model.Dificultad;

public record EditRecetaDto(
        String name,
        int tiempoPreparacion,
        Dificultad dificultad,
        Long categoriaId
) {
}
