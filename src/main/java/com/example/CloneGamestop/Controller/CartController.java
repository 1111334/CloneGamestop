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
        try {
            return ResponseEntity.ok(cartService.cart(cart));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/cart/{idUser}")
    public ResponseEntity<String> createCartAndAddUserById(@PathVariable Long idUser, @RequestBody Cart cart) {
        try {
            cartService.CartAndAddUserById(idUser, cart);
            return ResponseEntity.ok("Cart associated with User successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/associate-cart/{idUser}/{idCart}")
    public ResponseEntity<String> associateCartWithUser(@PathVariable Long idUser, @PathVariable Long idCart) {
        try {
            cartService.associateCartWithUser(idUser, idCart);
            return ResponseEntity.ok("Cart associated with User successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }




}
