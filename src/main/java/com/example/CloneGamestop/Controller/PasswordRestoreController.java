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
public class PasswordRestoreController {
    @Autowired
    private PasswordService passwordService;

    @PostMapping("/request")
    public void passwordRequest(@RequestBody RequestPasswordDTO requestPasswordDTO) {
        passwordService.request(requestPasswordDTO); //manda email
    }

    @PostMapping("/restore")
    public void passwordRestore(@RequestBody RestorePasswordDTO restorePasswordDTO) {
        passwordService.restore(restorePasswordDTO); //cambio password
    }
}
