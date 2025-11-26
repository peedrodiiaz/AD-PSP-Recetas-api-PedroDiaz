package com.salesianostriana.dam.proyectoapirecetas.Dto;

import com.salesianostriana.dam.proyectoapirecetas.Model.RecetaIngrediente;

public record IngredienteEnRecetaDto(
        String ingredienteNombre,
        String cantidad,
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
