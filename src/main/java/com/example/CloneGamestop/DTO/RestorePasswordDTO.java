package com.example.CloneGamestop.DTO; // Pacchetto che contiene la classe RestorePasswordDTO

import lombok.Data; // Importa l'annotazione @Data di Lombok

@Data // Annotazione Lombok per generare automaticamente i getter, setter, toString, equals e hashCode
public class RestorePasswordDTO { // Dichiarazione della classe RestorePasswordDTO

    private String newPassword; // Campo per la nuova password
    private String resetPasswordCode; // Campo per il codice di reset della password
}
