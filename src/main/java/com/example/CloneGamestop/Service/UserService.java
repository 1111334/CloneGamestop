package com.example.CloneGamestop.Service;

import com.example.CloneGamestop.DTO.SignupActivationDTO;
import com.example.CloneGamestop.DTO.UserDTO;
import com.example.CloneGamestop.Model.Cart;
import com.example.CloneGamestop.Model.Order;
import com.example.CloneGamestop.Model.Product;
import com.example.CloneGamestop.Model.User;
import com.example.CloneGamestop.Repository.CartRepository;
import com.example.CloneGamestop.Repository.OrderRepository;
import com.example.CloneGamestop.Repository.ProductRepository;
import com.example.CloneGamestop.Repository.UserRepository;
import com.example.CloneGamestop.notification.services.MailNotificationService;
import jakarta.transaction.Transactional;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

// Service per la gestione degli utenti
@Service
public class UserService {

    // Iniezione dei repository necessari e del servizio di notifica email
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private MailNotificationService mailNotificationService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    // Metodo per la registrazione di un nuovo utente
    public User signup(UserDTO userDTO) throws Exception {
        Optional<User> userInDB = userRepository.findByEmail(userDTO.getEmail());
        if (userInDB.isPresent()) throw new Exception("User already Exists");
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setSurname(userDTO.getSurname());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setActive(false);
        user.setActivationCode(UUID.randomUUID().toString());
        mailNotificationService.sendActivationService(user);
        return userRepository.save(user);
    }

    // Metodo per attivare l'account di un utente tramite codice di attivazione
    public User activate(SignupActivationDTO signupActivationDTO) throws Exception {
        User user =  userRepository.findByActivationCode(signupActivationDTO.getActivationCode());
        if (user == null) throw new Exception("User not found");
        user.setActive(true);
        user.setActivationCode(null);
        return userRepository.save(user);
    }

    // Metodo per la creazione di un nuovo utente
    public User users(UserDTO userDTO) throws Exception {
        Optional<User> existingUser = userRepository.findByEmail(userDTO.getEmail());
        if (existingUser.isPresent()) {
            throw new Exception("User already exists");
        }
        User newUser = new User();
        newUser.setUsername(userDTO.getUsername());
        newUser.setSurname(userDTO.getSurname());
        newUser.setEmail(userDTO.getEmail());
        newUser.setPassword(userDTO.getPassword());

        return userRepository.save(newUser);
    }

    //@Transactional
    public User addUserToProduct(Long idUser, Long idProduct) {
        Optional<User> optionalUser = userRepository.findById(idUser);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            Optional<Product> optionalProduct = productRepository.findById(idProduct);
            if (optionalProduct.isPresent()) {
                Product product = optionalProduct.get();
                user.getProducts().add(product);

                return userRepository.save(user);
            } else {
                throw new RuntimeException("User not found with ID: " + idUser);
            }
        } else {
            throw new RuntimeException("Product not found with ID: " + idProduct);
        }
    }

    // Metodo per creare un utente associandolo a un prodotto, un ordine e un carrello tramite i loro ID
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

    // Metodo per visualizzare un utente tramite ID
    public User viewUserDTOById(Long idUser) {
        return userRepository.findById(idUser).orElse(null);
    }

    // Metodo per ottenere tutti gli utenti
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Metodo per aggiornare un utente tramite ID e un oggetto User
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

    // Metodo per eliminare un utente tramite ID
    public UserDTO deleteUser(Long idUser) throws Exception {
        Optional<User> userOptional = userRepository.findById(idUser);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            userRepository.delete(user);
            return UserDTO.fromUser(user); // Restituisci l'utente eliminato come DTO
        } else {
            throw new Exception("User not found");
        }
    }
}
