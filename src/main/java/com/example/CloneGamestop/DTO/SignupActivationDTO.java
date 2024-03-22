package com.example.CloneGamestop.DTO; // Pacchetto che contiene la classe SignupActivationDTO

import lombok.Data; // Importa l'annotazione @Data di Lombok

@Data // Annotazione Lombok per generare automaticamente i getter, setter, toString, equals e hashCode
public class SignupActivationDTO { // Dichiarazione della classe SignupActivationDTO

    private String activationCode; // Campo per il codice di attivazione della registrazione
}
