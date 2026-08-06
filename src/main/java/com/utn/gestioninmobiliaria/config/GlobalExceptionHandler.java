package com.utn.gestioninmobiliaria.config;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgument(IllegalArgumentException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> handleDataIntegrity(DataIntegrityViolationException ex) {
        Map<String, String> response = new HashMap<>();
        
        if (ex.getMessage() != null && ex.getMessage().contains("ux_tipo_nro_documento")) {
            response.put("message", "Ya existe un registro con el mismo tipo y número de documento.");
        } else {
            response.put("message", "Error de integridad de datos en la base de datos.");
        }
        
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
}

