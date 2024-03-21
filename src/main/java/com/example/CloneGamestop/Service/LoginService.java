package com.example.CloneGamestop.Service;

import com.example.CloneGamestop.DTO.LoginDTO;
import com.example.CloneGamestop.DTO.LoginRTO;
import com.example.CloneGamestop.Model.User;
import com.example.CloneGamestop.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class LoginService {
    // Iniezione della dipendenza UserRepository
    @Autowired
    private UserRepository userRepository;

    // Metodo per gestire il login
    public LoginRTO login(LoginDTO loginDTO) {
        /** userRepository.findByEmail(loginDTO.getEmail())*/
        if (loginDTO == null) return null;

        // Verifica se il LoginDTO è nullo
        Optional<User> userFromDB = userRepository.findByEmail(loginDTO.getEmail());
        if (userFromDB.isEmpty() || !userFromDB.get().isActive()) return null;

        // Controlla se l'utente esiste nel database e se è attivo
        boolean canLogin = canUserLogin(userFromDB.get(), loginDTO.getPassword());
        if (!canLogin) return null;

        // Verifica se l'utente può effettuare il login
        String JWT = getJWT(userFromDB.get());
        // Imposto la data e ora di creazione del JWT sull'utente e salvo le modifiche nel database
        //User user = userFromDB.orElse(null);
        // Imposta il JWT creato sull'utente e salva le modifiche nel database
        userFromDB.get().setJwtCreatedOn(LocalDateTime.now());
        userRepository.save(userFromDB.get());


        userFromDB.get().setPassword(null);// per non rendere visibile la password alla creazione di un utente
        LoginRTO out = new LoginRTO();// Crea un oggetto LoginRTO per restituire i dati del login
        out.setJWT(JWT);
        out.setUser(userFromDB.get());

        return out;
    }

    // Metodo statico per verificare se l'utente può effettuare il login
    public static boolean canUserLogin(User user, String password) {
        return user.getPassword().equals(password);
    }

    // Metodo statico per ottenere un JSON Web Token (JWT) per l'utente
    public static String getJWT(User user) {
        return "---------------------------";
    }
}
