package com.salesianostriana.dam.proyectoapirecetas.Dto;

import com.salesianostriana.dam.proyectoapirecetas.Model.RecetaIngrediente;
import io.swagger.v3.oas.annotations.media.Schema;

public record IngredienteEnRecetaDto(
        @Schema(description = "Nombre del ingrediente que necesitamos para añadir a la receta", example = "Jamón")
        String ingredienteNombre,
        @Schema(description = "Cantidad que necesitamos de ese ingrediente", example = "3")
        double cantidad,
        @Schema (description = "Unidad que necesitamos en el ingrediente", example = "Loncha")
        String unidad
) {
    public static IngredienteEnRecetaDto of(RecetaIngrediente ri) {
        return new IngredienteEnRecetaDto(
                ri.getIngrediente().getNombre(),
                ri.getCantidad(),
                ri.getUnidad()
        );
    }
}
