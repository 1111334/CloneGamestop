package com.example.CloneGamestop.DTO; // Pacchetto che contiene la classe UserDTO

import com.example.CloneGamestop.Model.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;


// genera automaticamente un costruttore che accetta tutti i campi della classe User
@Data // Annotazione Lombok per generare automaticamente i getter, setter, toString, equals e hashCode
public class UserDTO { // Dichiarazione della classe UserDTO

    @NotBlank(message = "Lo username non può essere vuoto")
    @Size(min = 4, max = 50, message = "Lo username deve contenere tra 4 e 50 caratteri")
    private String username;// Campo per lo username dell'utente

    private String surname; // Campo per il cognome dell'utente

    @NotBlank(message = "L'email non può essere vuota")
    @Email(message = "Deve essere un indirizzo email valido")
    private String email;// Campo per l'email dell'utente

    @NotBlank(message = "La password non può essere vuota")
    @Size(min = 6, max = 100, message = "La password deve contenere tra 6 e 100 caratteri")
    private String password;// Campo per la password dell'utente

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
