package com.example.CloneGamestop.Service;

import com.example.CloneGamestop.Model.Cart;
import com.example.CloneGamestop.Model.Product;
import com.example.CloneGamestop.Model.User;
import com.example.CloneGamestop.Repository.CartRepository;
import com.example.CloneGamestop.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    public Cart cart (Cart cart) {
        return cartRepository.save(cart);
    }

    public Cart CartAndAddUserById(Long idUser, Cart cart) {
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + idUser));

        cart.setUser(user);
        return cartRepository.save(cart);
    }

    @Transactional
    public void associateCartWithUser(Long idUser, Long idCart) {
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + idUser));

        Cart cart = cartRepository.findById(idCart)
                .orElseThrow(() -> new RuntimeException("Cart not found with ID: " + idCart));

        cart.setUser(user);
        cartRepository.save(cart);

    }



}
