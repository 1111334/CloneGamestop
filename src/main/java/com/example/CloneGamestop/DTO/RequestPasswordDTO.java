package com.example.CloneGamestop.DTO; // Pacchetto che contiene la classe RequestPasswordDTO

import lombok.Data; // Importa l'annotazione @Data di Lombok

@Data // Annotazione Lombok per generare automaticamente i getter, setter, toString, equals e hashCode
public class RequestPasswordDTO { // Dichiarazione della classe RequestPasswordDTO

    private String email; // Campo per l'email richiesta per il ripristino della password
}
