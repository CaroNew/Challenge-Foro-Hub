package com.challenge.forohub.infra.errores;



import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.util.NoSuchElementException;

@RestControllerAdvice
public class TratadorDeErrores {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> validadorDeArgumentosHandler(MethodArgumentNotValidException e){
        var msj = e.getFieldErrors().stream().map(DatosError::new).toList();
        return ResponseEntity.badRequest().body(msj);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> validadorDeIntegridadHandler(DataIntegrityViolationException e){
        var causa = e.getMessage();
        return ResponseEntity.badRequest().body(causa);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> validadorDeIntegridadHandler2(NoSuchElementException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(ValidacionDeIntegridad.class)
    public ResponseEntity<?> validadorDeIntegridadHandler3(Exception e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }


    private record DatosError(String campo, String error) {
        public DatosError (FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }

}
