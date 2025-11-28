package com.salesianostriana.dam.proyectoapirecetas.Dto;

import com.salesianostriana.dam.proyectoapirecetas.Model.Ingrediente;
import io.swagger.v3.oas.annotations.media.Schema;

public record ResponseIngredienteDto(
        @Schema(description = "Id del ingrediente a devolver",example = "1")
        Long id,
        @Schema(description = "Nombre del ingrediente a devolver", example = "Azucar")
        String nombre
) {
    public static ResponseIngredienteDto of(Ingrediente ingrediente) {
        return new ResponseIngredienteDto(
                ingrediente.getId(),
                ingrediente.getNombre()
        );
    }
}
