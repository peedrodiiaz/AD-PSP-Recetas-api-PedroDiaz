package com.salesianostriana.dam.proyectoapirecetas.Dto;

import com.salesianostriana.dam.proyectoapirecetas.Model.Dificultad;
import com.salesianostriana.dam.proyectoapirecetas.Model.Receta;

public record ResponseRecetaDto(
        Long id,
        String name,
        int tiempoPreparacion,
        Dificultad dificultad,
        String categoriaName
) {
    public static ResponseRecetaDto of (Receta r){
        return  new ResponseRecetaDto(
                r.getId(),
                r.getName(),
                r.getTiempoPreparacion(),
                r.getDificultad(),
                r.getCategoria().getName()
        );
    }
}
