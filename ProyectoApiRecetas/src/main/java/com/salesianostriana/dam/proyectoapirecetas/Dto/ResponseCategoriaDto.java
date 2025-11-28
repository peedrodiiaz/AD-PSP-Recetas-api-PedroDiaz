package com.salesianostriana.dam.proyectoapirecetas.Dto;

import com.salesianostriana.dam.proyectoapirecetas.Model.Categoria;
import io.swagger.v3.oas.annotations.media.Schema;


public record ResponseCategoriaDto(
        @Schema(description = "Id de la receta que devolvemos", example = "1")
        Long id,
        @Schema(description = "Nombre de la receta que devolvemos",example = "Postres")
        String nombre,
        @Schema(description = "Descripción de la receta que devolvemos", example = "Para por la tarde")
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
