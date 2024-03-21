package com.example.CloneGamestop.DTO;

import com.example.CloneGamestop.Model.User;
import lombok.Data;

@Data
public class LoginRTO {
    private User user;
    private String JWT;
}
