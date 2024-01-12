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


@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    // Questo metodo riceve un oggetto Cart come argomento e lo salva utilizzando il repository.
    public Cart cart(Cart cart) {
        return cartRepository.save(cart);
    }


    public Cart CartAndAddUserById(Long idUser, Cart cart) {
        User user = userRepository.findById(idUser) //Trova un utente per ID. Se non trovato, lancia un'eccezione.
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + idUser));

        cart.setUser(user); //Imposta l'utente nel carrello e lo salva nel repository.
        return cartRepository.save(cart);
    }

    @Transactional //@Transactional garantisce che il metodo esegua in una singola transazione: commit se successo, rollback se errore.
    public void associateCartWithUser(Long idUser, Long idCart) {
        User user = userRepository.findById(idUser) //Cerca l'utente per ID; se non trovato, lancia un'eccezione.
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + idUser));

        Cart cart = cartRepository.findById(idCart) //Cerca il carrello per ID; se non trovato, lancia un'eccezione.
                .orElseThrow(() -> new RuntimeException("Cart not found with ID: " + idCart));

        cart.setUser(user); //Imposta l'utente nel carrello e lo salva nel repository.
        cartRepository.save(cart);
    }

    public List<Cart> viewListCart() {
        return cartRepository.findAll();
    }

    public Cart viewCartDTOById(Long idCart) {
        return cartRepository.findById(idCart).orElse(null);
    }

    public Cart updateCart(Long idCart, Cart updateCart) throws Exception {

        if (cartRepository.findById(idCart).isPresent()) {

            Cart cart = cartRepository.findById(idCart).get();

            if (Objects.nonNull(updateCart.getShippingAddress())) {
                cart.setShippingAddress(updateCart.getShippingAddress());
            }

            return cartRepository.save(cart);
        } else {
            throw new Exception(String.format("Cart with ID %s not found", idCart));
        }
    }

   public void deleteCartById(Long idCart) {
        cartRepository.getReferenceById(idCart);
   }


}
