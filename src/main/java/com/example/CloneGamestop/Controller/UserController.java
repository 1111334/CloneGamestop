package com.example.CloneGamestop.Controller;

import com.example.CloneGamestop.DTO.UserDTO;
import com.example.CloneGamestop.Model.User;
import com.example.CloneGamestop.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/create-user")
    public ResponseEntity createUser(@RequestBody User user) {
        try {
            return ResponseEntity.ok(userService.users(user)); // se tutto è ok crea lo user
        } catch (Exception e) { // altrimenti errore Http 400
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/createUserAssociate/{idProduct}/{idOrder}/{idCart}")
    public ResponseEntity createUserWithProductOrderCart(@RequestBody User user,
                                                         @PathVariable Long idProduct,
                                                         @PathVariable Long idOrder,
                                                         @PathVariable Long idCart) {

        User createdUser = userService.createUserWithProductOrderCart(user, idProduct, idOrder, idCart);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }


    @GetMapping(value = "/api/users/{idUser}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long idUser) {
        User user = userService.viewUserDTOById(idUser);

        if (user != null) {
            // Se l'utente è stato trovato, convertirlo in un DTO e restituirlo nella risposta
            UserDTO userDTO = UserDTO.fromUser(user);
            return new ResponseEntity<>(userDTO, HttpStatus.OK);
        } else {
            // Se l'utente non è stato trovato, restituire una risposta 404 Not Found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/view-all-user")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<User> userList = userService.getAllUsers();
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User user : userList) {
            userDTOList.add(UserDTO.fromUser(user));
        }
        return ResponseEntity.ok(userDTOList);
    }

    @PutMapping(value = "/update-user/{idUser}")
    public ResponseEntity updatedUser(@PathVariable Long idUser, @RequestBody User user) {
        try {
            return ResponseEntity.ok(userService.updateUser(idUser, user)); //se tutto ok aggiorna lo user con id specificato
        } catch (Exception e) { //altrimenti errore Http 400
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/delete-user/{idUser}")
    public ResponseEntity deleteUser(@PathVariable Long idUser) {
        try {
            return ResponseEntity.ok(userService.deletedUser(idUser)); //se tutto ok elimina lo user con id specificato
        } catch (Exception e) { //altrimenti errore Http 400
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}





