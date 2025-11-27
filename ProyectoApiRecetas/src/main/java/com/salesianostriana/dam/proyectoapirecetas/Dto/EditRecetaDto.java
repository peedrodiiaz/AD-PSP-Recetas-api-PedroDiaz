package com.salesianostriana.dam.proyectoapirecetas.Dto;

import com.salesianostriana.dam.proyectoapirecetas.Model.Dificultad;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO para crear o editar una receta")
public record EditRecetaDto(
        @Schema(description = "Nombre de la receta", example = "Paella valenciana")
        String name,

        @Schema(description = "Tiempo de preparación en minutos", example = "60")
        int tiempoPreparacion,

        @Schema(description = "Nivel de dificultad de la receta", example = "MEDIA")
        Dificultad dificultad,

        @Schema(description = "ID de la categoría a la que pertenece (debe existir)", example = "1")
        Long categoriaId
) {
}
