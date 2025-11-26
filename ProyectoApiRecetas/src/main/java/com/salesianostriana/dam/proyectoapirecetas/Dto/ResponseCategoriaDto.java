package com.salesianostriana.dam.proyectoapirecetas.Dto;

import com.salesianostriana.dam.proyectoapirecetas.Model.Categoria;
import com.salesianostriana.dam.proyectoapirecetas.Model.Receta;

import java.util.Set;

public record ResponseCategoriaDto(
        Long id,
        String name,
        String descripcion


) {
    public static ResponseCategoriaDto of(Categoria c){
        return  new ResponseCategoriaDto(
                c.getId(),
                c.getDescripcion(),
                c.getNombre()

        );
    }
}
