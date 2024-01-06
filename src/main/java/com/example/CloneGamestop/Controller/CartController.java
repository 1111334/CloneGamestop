package com.example.CloneGamestop.Controller;


import com.example.CloneGamestop.Model.Cart;
import com.example.CloneGamestop.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/create-cart")
    public ResponseEntity cartCreated(@RequestBody Cart cart) {
        try { //Il metodo cart() del servizio salva il carrello. Se riuscito, ritorna HTTP 200 OK con il carrello creato.
            return ResponseEntity.ok(cartService.cart(cart));
        } catch (Exception e) { //In caso di eccezione, ritorna HTTP 400 e il messaggio dell'eccezione come corpo
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/cart/{idUser}") //Crea un carrello e associa un utente per ID.
    public ResponseEntity<String> createCartAndAddUserById(@PathVariable Long idUser, @RequestBody Cart cart) {
        try {
            cartService.CartAndAddUserById(idUser, cart); //Se riuscito, ritorna un messaggio di successo.
            return ResponseEntity.ok("Cart associated with User successfully.");
        } catch (Exception e) { //In caso di errore, ritorna un messaggio d'errore.
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/associate-cart/{idUser}/{idCart}")
    public ResponseEntity<String> associateCartWithUser(@PathVariable Long idUser, @PathVariable Long idCart) {
        try {
            cartService.associateCartWithUser(idUser, idCart); //Associa un carrello a un utente specificato per ID.
            return ResponseEntity.ok("Cart associated with User successfully."); //Se riuscito, ritorna un messaggio di successo;
        } catch (Exception e) { //altrimenti, un messaggio d'errore.
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }




}
