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
    @GetMapping(value = "/api/cart/{idCart}")
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
    public ResponseEntity<List<CartDTO>> viewAllCart() {
        List<Cart> cartList = cartService.viewListCart();
        List<CartDTO> cartDTOList = new ArrayList<>();
        for (Cart cart : cartList) {
            cartDTOList.add(CartDTO.fromCart(cart));
        }
        return ResponseEntity.ok(cartDTOList);
    }

    @PutMapping(value = "update-cart/{idCart}")
    public ResponseEntity modifyCart(@PathVariable Long idCart, @RequestBody Cart cart) {
        try {
            return ResponseEntity.ok(cartService.updateCart(idCart, cart));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/delete-cart/{idCart}")
    public ResponseEntity<String> deleteCart(@PathVariable Long idCart) {
        try {
            cartService.deleteCartById(idCart);
            return ResponseEntity.ok("Il carrello è stato eliminato con successo.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Si è verificato un errore durante l'eliminazione del carrello.");
        }
    }

    //PROSSIMA VOLTA CREARE UN CARRELLO ASSOCIATO AD UNO USER COSI DA NON RISCONTRARE PROBLEMI NEL CANCELLARE UN CARRELLO





}
