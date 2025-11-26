package com.salesianostriana.dam.proyectoapirecetas.Dto;

import com.salesianostriana.dam.proyectoapirecetas.Model.Ingrediente;

public record ResponseIngredienteDto(
        Long id,
        String nombre
) {
    public static ResponseIngredienteDto of(Ingrediente ingrediente) {
        return new ResponseIngredienteDto(
                ingrediente.getId(),
                ingrediente.getNombre()
        );
    }
}
