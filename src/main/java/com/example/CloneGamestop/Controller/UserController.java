package com.example.CloneGamestop.Controller;

import com.example.CloneGamestop.Model.Cart;
import com.example.CloneGamestop.Model.User;
import com.example.CloneGamestop.Service.UserService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.transaction.Transactional;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create-user")
    public ResponseEntity createUser(@RequestBody User user) {
       try {
           return ResponseEntity.ok(userService.users(user)); // se tutto è ok crea lo user
       }catch (Exception e) { // altrimenti errore Http 400
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
       }
    }

   //CREARE UN DTO SIA PER RISOLVERE IL PROBLEMA DELLA INFINITY RECURSION E SIA PER VEDERE SOLO QUELLO CHE MENZIONO
   @GetMapping("/view-all-user")
    public ResponseEntity viewUser(){
        try {
            return ResponseEntity.ok(userService.viewAllUser()); //se tutto è ok visualizza tutti gli user
        }catch (Exception e) { //altrimenti errore Http 400
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
   }

   @GetMapping("view-user/{idUser}")
   public ResponseEntity viewUserById(@PathVariable Long idUser) {
        try {
           return ResponseEntity.ok(userService.viewUserByIdUser(idUser)); // se tutto è ok visualizza lo user con id specificato
        }catch (Exception e) { //altrimenti errore Http 400
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
   }

   @PutMapping("/update-user/{idUser}")
    public ResponseEntity updatedUser(@PathVariable Long idUser, @RequestBody User user) {
        try {
            return ResponseEntity.ok(userService.updateUser(idUser, user)); //se tutto ok aggiorna lo user con id specificato
        }catch (Exception e) { //altrimenti errore Http 400
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
   }

   @DeleteMapping("/delete-user/{idUser}")
    public ResponseEntity deleteUser(@PathVariable Long idUser) {
        try {
            return ResponseEntity.ok(userService.deletedUser(idUser)); //se tutto ok elimina lo user con id specificato
        }catch (Exception e) { //altrimenti errore Http 400
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
   }

}
