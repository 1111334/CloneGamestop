package com.example.CloneGamestop.Service;

import com.example.CloneGamestop.Model.Cart;
import com.example.CloneGamestop.Model.User;
import com.example.CloneGamestop.Repository.CartRepository;
import com.example.CloneGamestop.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    public User users(User user) {
       return userRepository.save(user);
    }

    //@Transactional
    //public void associateCartWithUser(Long idUser, Long idCart) {
    //    User user = userRepository.findById(idUser)
    //            .orElseThrow(() -> new RuntimeException("User not found with ID: " + idUser));

    //    Cart cart = cartRepository.findById(idCart)
    //            .orElseThrow(() -> new RuntimeException("Cart not found with ID: " + idCart));

    //    user.setCart(cart);
    //    userRepository.save(user);

    //}
}
