package com.example.CloneGamestop.Controller;

import com.example.CloneGamestop.DTO.LoginDTO;
import com.example.CloneGamestop.DTO.LoginRTO;
import com.example.CloneGamestop.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
// Indica che questo controller gestisce le richieste relative all'autenticazione e inizia dall'endpoint "/auth"
public class LoginController {
    @Autowired
    private LoginService loginService; // Iniezione della dipendenza del servizio di login

    @PostMapping("/login")
    // Metodo per gestire la richiesta di login
    public LoginRTO login(@RequestBody LoginDTO loginDTO) throws Exception {
        // Effettua il login e ottiene i risultati dal servizio di login
        LoginRTO loginRTO = loginService.login(loginDTO);
        // Se il risultato del login è nullo, lancia un'eccezione
        if (loginRTO == null) throw new Exception("Impossibile effettuare il login");
        // Altrimenti, restituisce i risultati del login
        return loginRTO;
    }


}
