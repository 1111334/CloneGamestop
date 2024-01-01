package com.example.CloneGamestop.Service;

import com.example.CloneGamestop.Model.User;
import com.example.CloneGamestop.Repository.UserRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User users(User user) {
       return userRepository.save(user);
    }
}
