package com.salesianostriana.dam.proyectoapirecetas.Error;

public class IngredienteYaAnadidoException extends RuntimeException {
    public IngredienteYaAnadidoException(String message) {
        super(message);
    }

    public IngredienteYaAnadidoException() {
        super("El ingrediente ya había sido añadido");
    }
}
