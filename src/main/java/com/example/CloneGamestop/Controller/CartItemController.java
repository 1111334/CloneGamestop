package com.example.CloneGamestop.Controller;

import com.example.CloneGamestop.Service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;
}
