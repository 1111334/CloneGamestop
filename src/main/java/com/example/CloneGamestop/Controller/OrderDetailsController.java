package com.example.CloneGamestop.Controller;

import com.example.CloneGamestop.Service.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderDetailsController {

    @Autowired
    private OrderDetailsService orderDetailsService;
}
