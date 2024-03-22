package com.example.CloneGamestop.jUnit;

import com.example.CloneGamestop.Model.Product;
import com.example.CloneGamestop.Repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
// Indica l'utilizzo del runner di Spring per eseguire i test.
@SpringBootTest
// Indica che si desidera caricare l'applicazione Spring Boot durante l'esecuzione dei test.
public class ProductTest {

    @Autowired
    // Inietta il bean ProductRepository all'interno della classe di test.
    private ProductRepository productRepository;

    @Test
    // Metodo di test
    public void testFindByProductId() {
        Long idProduct = 1L;
        // Cerca un prodotto per ID
        Optional<Product> optionalProduct = productRepository.findById(idProduct);
        // Ottiene il prodotto se presente, altrimenti restituisce null
        Product product = optionalProduct.orElse(null);
        // Verifica se l'ID del prodotto corrisponde all'ID atteso
        assertEquals(idProduct, product != null ? product.getIdProduct() : null);
    }
}
//Test jUnit da rivedere e approfondire l'argomento (ripassare il tutto).