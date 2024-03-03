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
//La classe è annotata con @RunWith(SpringRunner.class) che indica l'utilizzo del runner di Spring per eseguire i test.
@SpringBootTest
// viene utilizzato per indicare che si desidera caricare l'applicazione Spring Boot durante l'esecuzione dei test.
public class OrderTest {
    @Autowired
    //viene utilizzato per iniettare il bean ProductRepository all'interno della classe di test.
    private OrderRepository orderRepository;

    @Test
    public void testFindOrdersById() {
        Collection<Long> collectionsOrder = Arrays.asList(1L, 2L);

        for (Long id : collectionsOrder) {
            testFindOrderById(id);
        }
    }

    private void testFindOrderById(Long id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        Order order = optionalOrder.orElse(null);
        assertEquals(id, order != null ? order.getIdOrder() : null);
    }
}
