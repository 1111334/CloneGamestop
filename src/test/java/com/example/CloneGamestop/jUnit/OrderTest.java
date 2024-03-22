package com.example.CloneGamestop.jUnit;

import com.example.CloneGamestop.Model.Order;
import com.example.CloneGamestop.Repository.OrderRepository;
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
public class OrderTest {
    @Autowired
    // Inietta il bean OrderRepository all'interno della classe di test.
    private OrderRepository orderRepository;

    @Test
    // Metodo di test
    public void testFindOrdersById() {
        // Crea una collezione di ID degli ordini
        Collection<Long> collectionsOrder = Arrays.asList(1L, 2L);

        // Itera attraverso la collezione di ID degli ordini e chiama il metodo testFindOrderById per ciascuno
        for (Long id : collectionsOrder) {
            testFindOrderById(id);
        }
    }

    // Metodo per testare la ricerca di un ordine per ID
    private void testFindOrderById(Long id) {
        // Cerca un ordine per ID
        Optional<Order> optionalOrder = orderRepository.findById(id);
        // Ottiene l'ordine se presente, altrimenti restituisce null
        Order order = optionalOrder.orElse(null);
        // Verifica se l'ID dell'ordine corrisponde all'ID atteso
        assertEquals(id, order != null ? order.getIdOrder() : null);
    }
}
//Test jUnit da rivedere e approfondire l'argomento (ripassare il tutto).
