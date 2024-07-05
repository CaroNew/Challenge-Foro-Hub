package com.challenge.forohub.infra.errores;

public class ValidacionDeIntegridad extends RuntimeException{
    public ValidacionDeIntegridad(String mensaje){
        super(mensaje);
    }
}
