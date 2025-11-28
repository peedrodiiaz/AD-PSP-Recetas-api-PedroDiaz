package com.salesianostriana.dam.proyectoapirecetas.Dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record EditIngredienteDto(
        @Schema(description = "Nombre del ingrediente a editar o crear", example = "Salchicha")
        String nombre
) {
}
