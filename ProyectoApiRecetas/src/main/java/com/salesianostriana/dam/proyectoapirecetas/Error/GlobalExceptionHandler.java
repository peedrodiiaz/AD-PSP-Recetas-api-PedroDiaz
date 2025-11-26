package com.salesianostriana.dam.proyectoapirecetas.Error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NombreDuplicadoException.class)
    public ProblemDetail handleNombreDuplicado(NombreDuplicadoException ex) {
        ProblemDetail pd= ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, ex.getMessage());
        pd.setTitle(ex.getMessage());
        return  pd;

    }
    @ExceptionHandler(EntidadNoEncontradaException.class)
    public ProblemDetail handleNoEncontrada(EntidadNoEncontradaException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problem.setTitle(ex.getMessage());
        return problem;
    }

    @ExceptionHandler(TiempoInvalidoException.class)
    public ProblemDetail handleTiempoInvalido(TiempoInvalidoException ex){
        ProblemDetail pd= ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,ex.getMessage());
        pd.setTitle(ex.getMessage());
        return  pd;
    }
}