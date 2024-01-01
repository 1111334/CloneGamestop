package com.example.CloneGamestop.Controller;

import com.example.CloneGamestop.Model.Order;
import com.example.CloneGamestop.Model.Product;
import com.example.CloneGamestop.Service.ProductService;
import jakarta.persistence.PostRemove;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/create-product")
    public ResponseEntity Product(@RequestBody Product product) {
        try {
            return ResponseEntity.ok(productService.productCreate(product));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/create-product/{idOrder}")
    public ResponseEntity ProductByOrderId(@PathVariable Long idOrder, @RequestBody Product product) {
        try {
            return ResponseEntity.ok(productService.productCreateByOrderId(idOrder, product));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/product/{idUser}")
    public ResponseEntity ProductByUserId(@PathVariable Long idUser, @RequestBody Product product) {
        try {
            return ResponseEntity.ok(productService.productCreateByUserId(idUser, product));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
