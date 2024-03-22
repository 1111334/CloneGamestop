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
// Indica l'utilizzo del runner di Spring per eseguire i test.
@SpringBootTest
// Indica che si desidera caricare l'applicazione Spring Boot durante l'esecuzione dei test.
public class CartTest {
    @Autowired
    // Inietta il bean ProductRepository all'interno della classe di test.
    private CartRepository cartRepository;

    @Test
    // Metodo di test
    public void testFindCartsById() {
        // Crea una collezione di ID di carrelli
        Collection<Long> collectionCart = Arrays.asList(1L, 2L);

        // Itera attraverso la collezione di ID dei carrelli e chiama il metodo testFindCartById per ciascuno
        for (Long id : collectionCart) {
            testFindCartById(id);
        }
    }

    // Metodo per testare la ricerca di un carrello per ID
    private void testFindCartById(Long id) {
        // Cerca un carrello per ID
        Optional<Cart> optionalCart = cartRepository.findById(id);
        // Ottiene il carrello se presente, altrimenti restituisce null
        Cart cart = optionalCart.orElse(null);
        // Verifica se l'ID del carrello corrisponde all'ID atteso
        assertEquals(id, cart != null ? cart.getIdCart() : null);
    }
}

//Test jUnit da rivedere e approfondire l'argomento (ripassare il tutto).
