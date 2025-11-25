package com.salesianostriana.dam.proyectoapirecetas.Error;

public class NombreDuplicadoException extends RuntimeException {
    public NombreDuplicadoException(String message) {
        super(message);
    }
    public NombreDuplicadoException(){
        super("Ya existe una categoria con ese nombre");
    }
}
