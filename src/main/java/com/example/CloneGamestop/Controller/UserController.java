package com.example.CloneGamestop.Controller;

import com.example.CloneGamestop.Model.Cart;
import com.example.CloneGamestop.Model.User;
import com.example.CloneGamestop.Service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create-user")
    public ResponseEntity createUser(@RequestBody User user) {
       try {
           return ResponseEntity.ok(userService.users(user));
       }catch (Exception e) {
           return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
       }
    }

   //@PostMapping("/associate-cart/{idUser}/{idCart}")
   //public ResponseEntity<String> associateCartWithUser(@PathVariable Long idUser, @PathVariable Long idCart) {
   //    try {
   //        userService.associateCartWithUser(idUser, idCart);
   //        return ResponseEntity.ok("Cart associated with User successfully.");
   //    } catch (Exception e) {
   //        return ResponseEntity.badRequest().body(e.getMessage());
   //    }
   //}

}
