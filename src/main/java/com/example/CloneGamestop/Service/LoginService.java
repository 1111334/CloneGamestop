package com.example.CloneGamestop.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.CloneGamestop.DTO.LoginDTO;
import com.example.CloneGamestop.DTO.LoginRTO;
import com.example.CloneGamestop.Model.User;
import com.example.CloneGamestop.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    // Metodo per gestire il login
    public LoginRTO login(LoginDTO loginDTO) throws UnsupportedEncodingException {
        // Verifica se il LoginDTO è nullo
        if (loginDTO == null) return null;

        // Verifica se l'utente esiste nel database ed è attivo
        Optional<User> userFromDB = userRepository.findByEmail(loginDTO.getEmail());
        if (userFromDB.isEmpty() || !userFromDB.get().isActive()) return null;

        // Verifica se l'utente può effettuare il login confrontando le password
        boolean canLogin = canUserLogin(userFromDB.get(), loginDTO.getPassword());
        if (!canLogin) return null;

        // Genera il JWT e imposta il timestamp di creazione sull'utente, quindi salva le modifiche nel database
        String JWT = getJWT(userFromDB.get());
        userFromDB.get().setJwtCreatedOn(LocalDateTime.now());
        userRepository.save(userFromDB.get());

        // Crea un oggetto LoginRTO per restituire i dati del login
        userFromDB.get().setPassword(null); // Per evitare di esporre la password quando si restituiscono i dati dell'utente
        LoginRTO out = new LoginRTO();
        out.setJWT(JWT);
        out.setUser(userFromDB.get());

        return out;
    }

    // Metodo statico per verificare se l'utente può effettuare il login confrontando le password
    public boolean canUserLogin(User user, String password) {
        //return user.getPassword().equals(password);
       return passwordEncoder.matches(password, user.getPassword());
    }

    // Metodo statico per generare un JSON Web Token (JWT) per l'utente
    public static final String JWT_SECRET = "d573f999-ed10-4f67-ad43-4cd2c1d08cc5";

    // Genera il JWT con l'id dell'utente
    public static String getJWT(User user) throws UnsupportedEncodingException {
        return JWT.create().withClaim("id", user.getIdUser()).sign(Algorithm.HMAC512(JWT_SECRET));
    }
}
