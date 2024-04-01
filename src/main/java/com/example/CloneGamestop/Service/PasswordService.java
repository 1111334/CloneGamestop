package com.example.CloneGamestop.Service;

import com.example.CloneGamestop.DTO.RequestPasswordDTO;
import com.example.CloneGamestop.DTO.RestorePasswordDTO;
import com.example.CloneGamestop.Model.User;
import com.example.CloneGamestop.Repository.UserRepository;
import com.example.CloneGamestop.notification.services.MailNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

// Service per la gestione delle operazioni relative alle password
@Service
public class PasswordService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MailNotificationService mailNotificationService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    // Metodo per gestire la richiesta di ripristino della password
    public User request(RequestPasswordDTO requestPasswordDTO) throws Exception {
        // Implementazione del metodo per la gestione della richiesta di ripristino della password
        Optional<User> userFromDB = userRepository.findByEmail(requestPasswordDTO.getEmail());
        if (userFromDB.isEmpty()) throw new Exception("user is null");
        userFromDB.get().setPasswordResetCode(UUID.randomUUID().toString());
        mailNotificationService.sendPasswordResetMail(userFromDB);
        return userRepository.save(userFromDB.get());
    }

    // Metodo per gestire il ripristino della password
    public User restore(RestorePasswordDTO restorePasswordDTO) throws Exception {
        User userFromDB = userRepository.findByPasswordResetCode(restorePasswordDTO.getResetPasswordCode());
        if (userFromDB == null) throw new Exception("Cannot find user");
        userFromDB.setPassword(passwordEncoder.encode(restorePasswordDTO.getNewPassword()));
        userFromDB.setPasswordResetCode(null);

        //I am activating the user
        userFromDB.setActive(true);
        userFromDB.setActivationCode(null);

        return userRepository.save(userFromDB);
    }
}
