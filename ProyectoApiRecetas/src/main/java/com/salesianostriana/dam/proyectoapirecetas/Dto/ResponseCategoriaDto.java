package com.salesianostriana.dam.proyectoapirecetas.Dto;

import com.salesianostriana.dam.proyectoapirecetas.Model.Categoria;


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
