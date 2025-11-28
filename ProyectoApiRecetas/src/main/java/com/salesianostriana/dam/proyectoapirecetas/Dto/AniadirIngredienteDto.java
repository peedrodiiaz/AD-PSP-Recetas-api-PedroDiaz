package com.salesianostriana.dam.proyectoapirecetas.Dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record AniadirIngredienteDto(
        @Schema(description = "ID del ingrediente existente a añadir", example = "1")
        Long ingredienteId,

        @Schema(description = "Cantidad del ingrediente necesario para la receta", example = "200")
        double cantidad,

        @Schema(description = "Unidad de medida (gramos, litros, unidades, cucharadas, etc.)", example = "gramos")
        String unidad
) {
}
