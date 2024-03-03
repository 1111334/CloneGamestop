package com.example.CloneGamestop.Service;

import com.example.CloneGamestop.Model.Cart;
import com.example.CloneGamestop.Model.Order;
import com.example.CloneGamestop.Model.Product;
import com.example.CloneGamestop.Model.User;
import com.example.CloneGamestop.Repository.CartRepository;
import com.example.CloneGamestop.Repository.OrderRepository;
import com.example.CloneGamestop.Repository.ProductRepository;
import com.example.CloneGamestop.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartRepository cartRepository;

    public User users(User user) {
        return userRepository.save(user);
    } // salva l'user appena creato

    @Transactional
    public User createUserWithProductOrderCart(User user, Long productId, Long orderId, Long cartId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Cart not found with id: " + cartId));

        user.setProducts(Collections.singleton(product));
        user.setOrders(Collections.singleton(order));
        user.setCart(cart);

        return userRepository.save(user);
    }

    public User viewUserDTOById(Long idUser) {
        return userRepository.findById(idUser).orElse(null);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(Long idUser, User updateUser) throws Exception {

        if (userRepository.findById(idUser).isPresent()) {

            User user = userRepository.findById(idUser).get();

            if (Objects.nonNull(updateUser.getUsername())) {
                user.setUsername(updateUser.getUsername());
            }

            if (Objects.nonNull(updateUser.getPassword())) {
                user.setUsername(updateUser.getPassword());
            }

            if (Objects.nonNull(updateUser.getEmail())) {
                user.setUsername(updateUser.getEmail());
            }

            return userRepository.save(user);
        } else {
            throw new Exception(String.format("User with ID %s not found", idUser));
        }
    }

    public User deletedUser(Long idUser) {
        User user = userRepository.findById(idUser).get();
        userRepository.delete(user);
        return user;
    }
}
