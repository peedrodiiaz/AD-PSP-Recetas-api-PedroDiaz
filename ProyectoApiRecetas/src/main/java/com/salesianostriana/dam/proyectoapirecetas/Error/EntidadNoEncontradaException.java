package com.salesianostriana.dam.proyectoapirecetas.Error;

public class EntidadNoEncontradaException extends RuntimeException {
    public EntidadNoEncontradaException(String message) {
        super(message);
    }
    public EntidadNoEncontradaException(Long id) {
        super("Ingrediente no encontrado con id: %d".formatted(id));
    }




}
