package com.salesianostriana.dam.proyectoapirecetas.Error;

public class CategoriaNotFoundException extends EntidadNoEncontradaException {
    public CategoriaNotFoundException(String message) {
        super(message);
    }

    public CategoriaNotFoundException(){
        super("No se han encontrado categorias");
    }
    public CategoriaNotFoundException(Long id){
        super("No se han encontrado la categoría con ".formatted(id));
    }
}
