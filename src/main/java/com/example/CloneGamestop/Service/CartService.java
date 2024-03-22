package com.example.CloneGamestop.Service;

import com.example.CloneGamestop.Model.Cart;
import com.example.CloneGamestop.Model.User;
import com.example.CloneGamestop.Repository.CartRepository;
import com.example.CloneGamestop.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

// Service per la gestione delle operazioni relative ai carrelli
@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    // Metodo per salvare un carrello nel repository
    public Cart cart(Cart cart) {
        return cartRepository.save(cart);
    }

    // Metodo per associare un utente a un carrello utilizzando l'ID dell'utente e un oggetto Cart
    public Cart CartAndAddUserById(Long idUser, Cart cart) {
        // Trova l'utente per ID. Se non trovato, lancia un'eccezione.
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + idUser));
        // Imposta l'utente nel carrello e lo salva nel repository
        cart.setUser(user);
        return cartRepository.save(cart);
    }

    // Metodo transazionale per associare un carrello a un utente utilizzando gli ID
    @Transactional
    public void associateCartWithUser(Long idUser, Long idCart) {
        // Trova l'utente per ID. Se non trovato, lancia un'eccezione.
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + idUser));
        // Trova il carrello per ID. Se non trovato, lancia un'eccezione.
        Cart cart = cartRepository.findById(idCart)
                .orElseThrow(() -> new RuntimeException("Cart not found with ID: " + idCart));
        // Imposta l'utente nel carrello e lo salva nel repository.
        cart.setUser(user);
        cartRepository.save(cart);
    }

    // Metodo per visualizzare tutti i carrelli presenti
    public List<Cart> viewListCart() {
        return cartRepository.findAll();
    }

    // Metodo per visualizzare un carrello tramite ID
    public Cart viewCartDTOById(Long idCart) {
        return cartRepository.findById(idCart).orElse(null);
    }

    // Metodo per aggiornare un carrello utilizzando l'ID del carrello e un oggetto Cart
    public Cart updateCart(Long idCart, Cart updateCart) throws Exception {
        if (cartRepository.findById(idCart).isPresent()) {
            // Trova il carrello per ID
            Cart cart = cartRepository.findById(idCart).get();
            // Aggiorna l'indirizzo di spedizione se presente nell'oggetto di aggiornamento
            if (Objects.nonNull(updateCart.getShippingAddress())) {
                cart.setShippingAddress(updateCart.getShippingAddress());
            }
            // Salva il carrello aggiornato nel repository
            return cartRepository.save(cart);
        } else {
            // Se il carrello non è trovato, solleva un'eccezione
            throw new Exception(String.format("Cart with ID %s not found", idCart));
        }
    }

    // Metodo per eliminare un carrello utilizzando l'ID del carrello
    public void deleteCartById(Long idCart) {
        // Utilizza getReferenceById per ottenere una reference all'entità e rimuoverla
        cartRepository.getReferenceById(idCart);
    }
}
