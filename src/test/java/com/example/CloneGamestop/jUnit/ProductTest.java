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
//La classe è annotata con @RunWith(SpringRunner.class) che indica l'utilizzo del runner di Spring per eseguire i test.
@SpringBootTest
// viene utilizzato per indicare che si desidera caricare l'applicazione Spring Boot durante l'esecuzione dei test.
public class ProductTest {

    @Autowired
    //viene utilizzato per iniettare il bean ProductRepository all'interno della classe di test.
    private ProductRepository productRepository;

    @Test
    public void testFindByProductId() {
        Long idProduct = 1L;
        Optional<Product> optionalProduct = productRepository.findById(idProduct);
        Product product = optionalProduct.orElse(null);
        assertEquals(idProduct, product != null ? product.getIdProduct() : null);
    }
}
