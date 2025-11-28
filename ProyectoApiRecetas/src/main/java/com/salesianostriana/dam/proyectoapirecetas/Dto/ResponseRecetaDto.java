package com.salesianostriana.dam.proyectoapirecetas.Dto;

import com.salesianostriana.dam.proyectoapirecetas.Model.Dificultad;
import com.salesianostriana.dam.proyectoapirecetas.Model.Receta;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record ResponseRecetaDto(
        @Schema(description = "Id de la receta a devolver", example = "1")
        Long id,
        @Schema(description = "Nombre de la receta", example = "Paella Valenciana")
        String name,
        @Schema(description = "Tiempo de preparación en minutos", example = "45")
        int tiempoPreparacion,
        @Schema(description = "Nivel de dificultad de la receta", example = "MEDIO")
        Dificultad dificultad,
        @Schema(description = "Nombre de la categoría a la que pertenece la receta", example = "Arroces")
        String categoriaName,
        @Schema(description = "Lista de ingredientes con sus cantidades y unidades de medida")
        List<IngredienteEnRecetaDto>ingredientes


) {
    public static ResponseRecetaDto of (Receta r){
        return  new ResponseRecetaDto(
                r.getId(),
                r.getName(),
                r.getTiempoPreparacion(),
                r.getDificultad(),
                r.getCategoria().getNombre(),
                r.getRecetaIngredientes().stream()
                        .map(IngredienteEnRecetaDto::of)
                        .toList()
        );
    }
}
