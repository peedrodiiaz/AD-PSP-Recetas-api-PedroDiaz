package com.salesianostriana.dam.proyectoapirecetas.Error;

public class TiempoInvalidoException extends RuntimeException {
    public TiempoInvalidoException(String message) {
        super(message);
    }

    public TiempoInvalidoException(){
        super("El tiempo de preparación es menor o igual a 0");
    }


}
