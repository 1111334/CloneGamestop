package com.example.CloneGamestop.Controller;

import com.example.CloneGamestop.DTO.CartDTO;
import com.example.CloneGamestop.Model.Cart;
import com.example.CloneGamestop.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
// Indica che questa classe è un controller REST e restituisce risposte JSON
public class CartController {
    @Autowired
    private CartService cartService; // Iniezione della dipendenza del servizio di carrello

    @PostMapping("/create-cart")
    // Metodo per gestire la creazione del carrello
    public ResponseEntity cartCreated(@RequestBody Cart cart) {
        // Il metodo cart() del servizio salva il carrello. Se riuscito, ritorna HTTP 200 OK con il carrello creato.
        return ResponseEntity.ok(cartService.cart(cart));
    }

    @PostMapping("/cart/{idUser}")
    // Crea un carrello e associa un utente per ID
    public ResponseEntity createCartAndAddUserById(@PathVariable Long idUser, @RequestBody Cart cart) {
        // Se riuscito, ritorna un messaggio di successo
        return ResponseEntity.ok(cartService.CartAndAddUserById(idUser, cart));
    }

    @PostMapping("/associate-cart/{idUser}/{idCart}")
    // Associa un carrello a un utente specificato per ID
    public void associateCartWithUser(@PathVariable Long idUser, @PathVariable Long idCart) {
           cartService.associateCartWithUser(idUser, idCart);
    }

    @GetMapping(value = "/api/cart/{idCart}")
    // Ottiene un carrello tramite ID e restituisce un DTO del carrello
    public ResponseEntity<CartDTO> getUserById(@PathVariable Long idCart) {
        Cart cart = cartService.viewCartDTOById(idCart);

        if (cart != null) {
            // Se il carrello è stato trovato, convertirlo in un DTO e restituirlo nella risposta
            CartDTO cartDTO = CartDTO.fromCart(cart);
            return new ResponseEntity<>(cartDTO, HttpStatus.OK);
        } else {
            // Se il carrello non è stato trovato, restituire una risposta 404 Not Found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/view-all-cart")
    // Ottiene tutti i carrelli e restituisce una lista di DTO dei carrelli
    public ResponseEntity<List<CartDTO>> viewAllCart() {
        List<Cart> cartList = cartService.viewListCart();
        List<CartDTO> cartDTOList = new ArrayList<>();
        for (Cart cart : cartList) {
            cartDTOList.add(CartDTO.fromCart(cart)); // Converti ogni carrello in DTO e aggiungilo alla lista
        }
        return ResponseEntity.ok(cartDTOList); // Restituisce la lista di DTO dei carrelli
    }

    @PutMapping(value = "update-cart/{idCart}")
    // Metodo per modificare un carrello esistente
    public ResponseEntity modifyCart(@PathVariable Long idCart, @RequestBody Cart cart) throws Exception {
            // Modifica il carrello e restituisce una risposta OK
            return ResponseEntity.ok(cartService.updateCart(idCart, cart));
    }

    @DeleteMapping(value = "/delete-cart/{idCart}")
    // Metodo per eliminare un carrello esistente
    public ResponseEntity<String> deleteCart(@PathVariable Long idCart) {
            cartService.deleteCartById(idCart); // Elimina il carrello
            return ResponseEntity.ok("Il carrello è stato eliminato con successo.");
    }

    // DA IMPLEMENTARE: CREARE UN CARRELLO ASSOCIATO AD UN UTENTE PER EVITARE PROBLEMI NELLA CANCELLAZIONE DEI CARRELLI
}
