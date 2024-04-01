package com.example.CloneGamestop.Controller;

import com.example.CloneGamestop.DTO.RequestPasswordDTO;
import com.example.CloneGamestop.DTO.RestorePasswordDTO;
import com.example.CloneGamestop.Service.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/password")
// Indica che questa classe è un controller REST e tutte le richieste iniziano con il percorso "/auth/password"
public class PasswordRestoreController {
    @Autowired
    private PasswordService passwordService; // Utilizza il servizio PasswordService per eseguire operazioni relative al ripristino della password

    @PostMapping("/request")
    // Metodo per gestire la richiesta di ripristino della password
    public void passwordRequest(@RequestBody RequestPasswordDTO requestPasswordDTO) throws Exception {
        try {
            passwordService.request(requestPasswordDTO); // Invia l'email per la richiesta di ripristino della password
        }catch (Exception e){

        }
    }

    @PostMapping("/restore")
    // Metodo per gestire il ripristino effettivo della password
    public void passwordRestore(@RequestBody RestorePasswordDTO restorePasswordDTO) throws Exception {
        passwordService.restore(restorePasswordDTO); // Effettua il cambio effettivo della password
    }
}
