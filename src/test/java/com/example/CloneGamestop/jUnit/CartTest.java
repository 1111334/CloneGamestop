package com.example.CloneGamestop.jUnit;

import com.example.CloneGamestop.Model.Cart;
import com.example.CloneGamestop.Repository.CartRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
//La classe è annotata con @RunWith(SpringRunner.class) che indica l'utilizzo del runner di Spring per eseguire i test.
@SpringBootTest
// viene utilizzato per indicare che si desidera caricare l'applicazione Spring Boot durante l'esecuzione dei test.
public class CartTest {
    @Autowired
    //viene utilizzato per iniettare il bean ProductRepository all'interno della classe di test.
    private CartRepository cartRepository;

    @Test
    public void testFindCartsById() {
        Collection<Long> collectionCart = Arrays.asList(1L, 2L);

        for (Long id : collectionCart) {
            testFindCartById(id);
        }
    }

    private void testFindCartById(Long id) {
        Optional<Cart> optionalCart = cartRepository.findById(id);
        Cart cart = optionalCart.orElse(null);
        assertEquals(id, cart != null ? cart.getIdCart() : null);
    }

}
