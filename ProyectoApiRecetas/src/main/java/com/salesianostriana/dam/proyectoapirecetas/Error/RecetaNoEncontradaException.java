package com.salesianostriana.dam.proyectoapirecetas.Error;

public class RecetaNoEncontradaException extends EntidadNoEncontradaException {
    public RecetaNoEncontradaException(String message) {
        super(message);
    }

    public RecetaNoEncontradaException(Long id){
        super("No se han encontrado la receta con ID ".formatted(id));
    }
}
