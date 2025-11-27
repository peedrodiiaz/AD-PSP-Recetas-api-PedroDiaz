package com.salesianostriana.dam.proyectoapirecetas.Dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO para añadir un ingrediente a una receta con cantidad y unidad específicas. " +
        "Establece la relación Many-to-Many entre Receta e Ingrediente con datos adicionales.")
public record AniadirIngredienteDto(
        @Schema(description = "ID del ingrediente existente a añadir", example = "1")
        Long ingredienteId,

        @Schema(description = "Cantidad del ingrediente necesario para la receta", example = "200")
        String cantidad,

        @Schema(description = "Unidad de medida (gramos, litros, unidades, cucharadas, etc.)", example = "gramos")
        String unidad
) {
}
