package com.salesianostriana.dam.proyectoapirecetas.Error;

import java.util.function.Supplier;

public class CategoriaInvalidaException extends RuntimeException {
    public CategoriaInvalidaException(String message) {
        super(message);
    }

    public CategoriaInvalidaException(Long id){
        super("Categoría introducida no existente id= %d".formatted(id));
    }
}
