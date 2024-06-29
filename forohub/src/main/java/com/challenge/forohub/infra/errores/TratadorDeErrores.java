package com.challenge.forohub.infra.errores;



import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErrores {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity validadorDeIntegridadHandler(DataIntegrityViolationException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }


}
