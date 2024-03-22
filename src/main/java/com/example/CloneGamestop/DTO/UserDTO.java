package com.example.CloneGamestop.DTO; // Pacchetto che contiene la classe UserDTO

import com.example.CloneGamestop.Model.User; // Importa la classe User dal pacchetto Model
import lombok.Data; // Importa l'annotazione @Data di Lombok

@Data // Annotazione Lombok per generare automaticamente i getter, setter, toString, equals e hashCode
public class UserDTO { // Dichiarazione della classe UserDTO

    private String username; // Campo per lo username dell'utente
    private String surname; // Campo per il cognome dell'utente
    private String email; // Campo per l'email dell'utente
    private String password; // Campo per la password dell'utente

    public UserDTO() { // Costruttore vuoto
    }

    // Metodo statico per creare un oggetto UserDTO da un oggetto User
    public static UserDTO fromUser(User user) {
        UserDTO userDTO = new UserDTO(); // Crea un nuovo oggetto UserDTO
        userDTO.setUsername(user.getUsername()); // Imposta lo username
        userDTO.setSurname(user.getSurname()); // Imposta il cognome
        userDTO.setEmail(user.getEmail()); // Imposta l'email
        userDTO.setPassword(user.getPassword()); // Imposta la password
        return userDTO; // Restituisce l'oggetto UserDTO
    }

    // Metodo statico per creare un oggetto User da un oggetto UserDTO
    public static User toUser(UserDTO userDTO) {
        User user = new User(); // Crea un nuovo oggetto User
        user.setUsername(userDTO.getUsername()); // Imposta lo username
        user.setEmail(userDTO.getEmail()); // Imposta l'email
        user.setPassword(userDTO.getPassword()); // Imposta la password
        return user; // Restituisce l'oggetto User
    }
}
