package com.salesianostriana.dam.proyectoapirecetas.Dto;

import com.salesianostriana.dam.proyectoapirecetas.Model.Categoria;

public record GetCategoriaDto(
        Long id,
        String name,
        String descripcion
) {
    public static GetCategoriaDto of(Categoria c){
        return  new GetCategoriaDto(
                c.getId(),
                c.getDescripcion(),
                c.getName()
        );
    }
}
