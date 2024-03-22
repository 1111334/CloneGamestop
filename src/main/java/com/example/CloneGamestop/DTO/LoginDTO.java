package com.example.CloneGamestop.DTO; // Package che contiene la classe LoginDTO

import lombok.Data; // Importa l'annotazione @Data di Lombok

@Data // Annotazione Lombok per generare automaticamente i getter, setter, toString, equals e hashCode
public class LoginDTO { // Dichiarazione della classe LoginDTO

    /* Questo è l'email dell'utente */
    private String email; // Campo per l'email dell'utente

    /* Questa è la password NON CIFRATA */
    private String password; // Campo per la password dell'utente
}
