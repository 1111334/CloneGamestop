package com.example.CloneGamestop.Controller;

import com.example.CloneGamestop.DTO.SignupActivationDTO;
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
@RequestMapping("/auth")
// Indica che questo controller gestisce le richieste relative all'autenticazione
public class UserController {
    @Autowired
    private UserService userService; // Utilizza il servizio UserService per eseguire operazioni relative agli utenti

    @PostMapping("/signup")
// Metodo per gestire la registrazione di un nuovo utente
    public void access(@RequestBody UserDTO userDTO) throws Exception {
        userService.signup(userDTO); //PROBLEMI CON SIGNUP CAUSA PROBLEMI IN INTELLIJ NONOSTANTE l'endpoint vada a buon fine
    }

    @PostMapping("/activation")
// Metodo per gestire l'attivazione dell'account utente
    public void activation(@RequestBody SignupActivationDTO signupActivationDTO) throws Exception {
        userService.activate(signupActivationDTO);
    }

    @PostMapping(value = "/create-user")
    // Metodo per creare un nuovo utente
    public ResponseEntity createUser(@RequestBody UserDTO userDTO) throws Exception {
        // Crea un nuovo utente e restituisce una risposta OK con i dati dell'utente creato
        return ResponseEntity.ok(userService.users(userDTO));
    }

    @PostMapping("/addUserToProduct/{idUser}/{idProduct}")
    public ResponseEntity addIdUserToProductId(@PathVariable Long idUser, @PathVariable Long idProduct) {
        return ResponseEntity.ok(userService.addUserToProduct(idUser, idProduct));
    }

    @PostMapping("/createUserAssociate/{idProduct}/{idOrder}/{idCart}")
    // Metodo per creare un utente associato a un prodotto, un ordine e un carrello
    public ResponseEntity createUserWithProductOrderCart(@RequestBody User user,
                                                         @PathVariable Long idProduct,
                                                         @PathVariable Long idOrder,
                                                         @PathVariable Long idCart) {
        User createdUser = userService.createUserWithProductOrderCart(user, idProduct, idOrder, idCart);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @GetMapping(value = "/api/users/{idUser}")
    // Metodo per ottenere un utente tramite ID e restituire un DTO dell'utente
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
    // Metodo per ottenere tutti gli utenti e restituire una lista di DTO degli utenti
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<User> userList = userService.getAllUsers();
        List<UserDTO> userDTOList = new ArrayList<>();
        for (User user : userList) {
            userDTOList.add(UserDTO.fromUser(user)); // Converti ogni utente in DTO e aggiungilo alla lista
        }
        return ResponseEntity.ok(userDTOList); // Restituisce la lista di DTO degli utenti
    }

    @PutMapping(value = "/update-user/{idUser}")
    // Metodo per aggiornare un utente esistente
    public ResponseEntity updatedUser(@PathVariable Long idUser, @RequestBody User user) throws Exception {
        // Aggiorna l'utente e restituisce una risposta OK
        return ResponseEntity.ok(userService.updateUser(idUser, user));
    }

    @DeleteMapping(value = "/delete-user/{idUser}")
    // Metodo per eliminare un utente esistente
    public ResponseEntity<UserDTO> deleteUser(@PathVariable Long idUser) throws Exception {
        // Elimina l'utente e restituisce una risposta OK con i dati dell'utente eliminato
        return ResponseEntity.ok(userService.deleteUser(idUser));
    }
}



