package com.salesianostriana.dam.proyectoapirecetas.Dto;

import com.salesianostriana.dam.proyectoapirecetas.Model.Dificultad;
import com.salesianostriana.dam.proyectoapirecetas.Model.Receta;

import java.util.List;

public record ResponseRecetaDto(
        Long id,
        String name,
        int tiempoPreparacion,
        Dificultad dificultad,
        String categoriaName,
        List<IngredienteEnRecetaDto>ingredientes

) {
    public static ResponseRecetaDto of (Receta r){
        return  new ResponseRecetaDto(
                r.getId(),
                r.getName(),
                r.getTiempoPreparacion(),
                r.getDificultad(),
                r.getCategoria().getName(),
                r.getRecetaIngredientes().stream()
                        .map(IngredienteEnRecetaDto::of)
                        .toList()
        );
    }
}
