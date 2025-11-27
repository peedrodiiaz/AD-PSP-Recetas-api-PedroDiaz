package com.salesianostriana.dam.proyectoapirecetas.Dto;

import com.salesianostriana.dam.proyectoapirecetas.Model.Categoria;
import com.salesianostriana.dam.proyectoapirecetas.Model.Receta;

import java.util.Set;

public record ResponseCategoriaDto(
        Long id,
        String nombre,
        String descripcion


) {
    public static ResponseCategoriaDto of(Categoria c){
        return  new ResponseCategoriaDto(
                c.getId(),
                c.getNombre(),
                c.getDescripcion()


        );
    }
}
