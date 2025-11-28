package com.salesianostriana.dam.proyectoapirecetas.Dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Dto para editar o crear una categoria ")
public record EditCategoriaDto(
        @Schema(description = "Nombre de la categoria a editar o crear", example = "200")
        String nombre,
        @Schema(description = "Descripción de la categoria a editar o crear", example = "gramos")
        String descripcion )
{


}