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

    @PostMapping("/create-product") //crea un prodotto
    public ResponseEntity Product(@RequestBody Product product) {
        try { // se è tutto ok Http 200 ritorna il prodotto creato
            return ResponseEntity.ok(productService.productCreate(product));
        }catch (Exception e) { // altrimenti errore Http 400
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("create-product/{idOrder}/{idUser}")
    public ResponseEntity ProductByOrderIdAndByUserId(@PathVariable Long idOrder,
                                                      @PathVariable Long idUser,
                                                      @RequestBody Product product) {
        try {
            return ResponseEntity.ok(productService.productCreateByOrderIdAndByUserId(idOrder, idUser, product));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/add-to-cart/{idProduct}/{idCart}") //Aggiunge un prodotto a un carrello specificato per ID.
    public ResponseEntity addProductToCartById(@PathVariable Long idProduct, @PathVariable Long idCart) {
        try { //Se riuscito, ritorna una risposta HTTP 200 OK.
            return ResponseEntity.ok(productService.addProductToCart(idProduct, idCart));
        }catch (Exception e) { //altrimenti errore Http 400
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    //CREARE UN DTO SIA PER RISOLVERE IL PROBLEMA DELLA INFINITY RECURSION E SIA PER VEDERE SOLO QUELLO CHE MENZIONO
    @GetMapping("/view-all-product")
    public ResponseEntity viewAllProduct() {
        try {
            return ResponseEntity.ok(productService.viewListProduct());
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/view-product/{idProduct}")
    public ResponseEntity viewProductById(@PathVariable Long idProduct) {
        try {
            return ResponseEntity.ok(productService.viewProductByidProduct(idProduct));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }





}
