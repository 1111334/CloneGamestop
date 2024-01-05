package com.example.CloneGamestop.Controller;

import com.example.CloneGamestop.Model.Cart;
import com.example.CloneGamestop.Model.Order;
import com.example.CloneGamestop.Model.Product;
import com.example.CloneGamestop.Service.ProductService;
import jakarta.persistence.PostRemove;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
            return ResponseEntity.ok(productService.productCreateByUserId(idUser,product));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    //@PostMapping("/product-create/{idCart}")
    //public ResponseEntity<?> createProductByCartId(@PathVariable Long idCart, @RequestBody Product product) {
    //    try {
    //        Product createdProduct = productService.createProductAndAdd(idCart, product);
    //        return ResponseEntity.ok(createdProduct);
    //    } catch (Exception e) {
    //        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    //    }
    //}

    @PostMapping("/add-to-cart/{idProduct}/{idCart}")
    public ResponseEntity addProductToCartById(@PathVariable Long idProduct, @PathVariable Long idCart) {
        try {
            return ResponseEntity.ok(productService.addProductToCart(idProduct, idCart));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }





}
