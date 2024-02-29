package com.example.CloneGamestop.Service;

import com.example.CloneGamestop.Model.User;
import com.example.CloneGamestop.Repository.CartRepository;
import com.example.CloneGamestop.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    public User users(User user) {
        return userRepository.save(user);
    } // salva l'user appena creato

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
