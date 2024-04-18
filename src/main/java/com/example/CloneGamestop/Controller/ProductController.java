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
// Indica che questa classe è un controller REST e restituisce risposte JSON
public class ProductController {

    @Autowired
    private ProductService productService; // Utilizza il servizio ProductService per eseguire operazioni relative ai prodotti

    @PostMapping("/create-product")
    // Metodo per gestire la creazione di un prodotto
    public ResponseEntity createProduct(@RequestBody Product product) {
        // Il metodo productCreate() del servizio salva il prodotto. Se riuscito, ritorna HTTP 200 OK con il prodotto creato
        return ResponseEntity.ok(productService.productCreate(product));
    }

    @PostMapping("create-product/{idOrder}/{idUser}")
    // Metodo per creare un prodotto per un determinato ID ordine e ID utente
    public ResponseEntity ProductByOrderIdAndByUserId(@PathVariable Long idOrder,
                                                      @PathVariable Long idUser,@RequestBody Product product) {
            // Crea un prodotto per l'ID ordine e l'ID utente specificati utilizzando ProductService
            return ResponseEntity.ok(productService.productCreateByOrderIdAndByUserId(idOrder, idUser, product));
    }

    @PostMapping("/add-to-cart/{idProduct}/{idCart}")
    // Metodo per aggiungere un prodotto a un carrello specificato per ID
    public ResponseEntity addProductToCartById(@PathVariable Long idProduct, @PathVariable Long idCart) {
            // Aggiunge il prodotto al carrello specificato utilizzando ProductService
            return ResponseEntity.ok(productService.addProductToCart(idProduct, idCart));
    }

    @GetMapping(value = "/api/product/{idProduct}")
    // Ottiene un prodotto tramite ID e restituisce un DTO del prodotto
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

    @GetMapping("/view-all-product")
    // Ottiene tutti i prodotti e restituisce una lista di DTO dei prodotti
    public ResponseEntity<List<ProductDTO>> viewAllProduct() {
        List<Product> productList = productService.viewListProduct();
        List<ProductDTO> productDTOList = new ArrayList<>();
        for (Product product : productList) {
            productDTOList.add(ProductDTO.fromProduct(product)); // Converti ogni prodotto in DTO e aggiungilo alla lista
        }
        return ResponseEntity.ok(productDTOList); // Restituisce la lista di DTO dei prodotti
    }

    @PutMapping(value = "/update-product/{idProduct}")
    // Metodo per modificare un prodotto esistente
    public ResponseEntity modifyProductById(@PathVariable Long idProduct, @RequestBody Product product) throws Exception {
            // Modifica il prodotto e restituisce una risposta OK
            return ResponseEntity.ok(productService.updatedProduct(idProduct, product));
    }

    @DeleteMapping(value = "/delete-product/{idProduct}")
    // Metodo per eliminare un prodotto esistente
    public ResponseEntity<String> deleteProductById(@PathVariable Long idProduct) {
            // Elimina il prodotto utilizzando ProductService
            productService.deleteProductByIdProduct(idProduct);
            return ResponseEntity.ok("Prodotto eliminato con successo");
    }
}
