package com.example.CloneGamestop.Controller;

import com.example.CloneGamestop.Model.User;
import com.example.CloneGamestop.Service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create-user")
    public ResponseEntity createUser(@RequestBody User user) {
       try {
           return ResponseEntity.ok(userService.users(user));
       }catch (Exception e) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
       }
    }

    //Implementare richiesta Post tramite id che si colleghi a ordine,prodotto,carrello

    //@PostMapping("/create-user/{id}")

}
