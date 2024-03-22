package com.example.CloneGamestop.DTO; // Package che contiene la classe LoginRTO

import com.example.CloneGamestop.Model.User; // Importa la classe User dal package Model
import lombok.Data; // Importa l'annotazione @Data di Lombok

@Data // Annotazione Lombok per generare automaticamente i getter, setter, toString, equals e hashCode
public class LoginRTO { // Dichiarazione della classe LoginRTO

    private User user; // Campo per l'utente
    private String JWT; // Campo per il token JWT
}
