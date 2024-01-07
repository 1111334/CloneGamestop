package com.example.CloneGamestop.Controller;

import com.example.CloneGamestop.DTO.ProductDTO;
import com.example.CloneGamestop.Model.Product;
import com.example.CloneGamestop.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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


    @GetMapping("/view-all-product")
    public ResponseEntity<List<ProductDTO>> viewAllProduct() {
       List<Product> productList = productService.viewListProduct();
       List<ProductDTO> productDTOList = new ArrayList<>();
       for (Product product : productList) {
           productDTOList.add(ProductDTO.fromProduct(product));
       }
       return ResponseEntity.ok(productDTOList);
    }

    @GetMapping(value = "/api/product/{idProduct}")
    public ResponseEntity<ProductDTO> getUserById(@PathVariable Long idProduct) {
        Product product = productService.viewProductDTOById(idProduct);

        if (product != null) {
            // Se il prodotto è stato trovato, convertirlo in un DTO e restituirlo nella risposta
            ProductDTO productDTO = ProductDTO.fromProduct(product);
            return new ResponseEntity<>(productDTO, HttpStatus.OK);
        } else {
            // Se il prodotto non è stato trovato, restituire una risposta 404 Not Found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }





}
