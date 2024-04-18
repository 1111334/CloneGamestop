package com.example.CloneGamestop.Exception;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ConcreteErrorResponse {
    private String message;
    private LocalDateTime timestamp;
    private int errorCode;

    public ConcreteErrorResponse(String message) {
        this.message = message + "not found";
        this.timestamp = LocalDateTime.now(); // Imposta il timestamp corrente
        // Puoi anche impostare un valore predefinito per l'errorCode, se necessario
        this.errorCode = 0; // Esempio di valore predefinito
    }
}