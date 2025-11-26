package com.salesianostriana.dam.proyectoapirecetas.Error;

public class NombreDuplicadoException extends RuntimeException {
    public NombreDuplicadoException(String message) {
        super(message);
    }
    public NombreDuplicadoException(){
        super("Ya existe una entidad con ese nombre");
    }
}
