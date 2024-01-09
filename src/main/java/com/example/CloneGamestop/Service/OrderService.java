package com.example.CloneGamestop.Service;

import com.example.CloneGamestop.Model.Order;
import com.example.CloneGamestop.Model.User;
import com.example.CloneGamestop.Repository.OrderRepository;
import com.example.CloneGamestop.Repository.ProductRepository;
import com.example.CloneGamestop.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderService {

    //l'annotazione @Autowired evita la necessità di creare manualmente le istanze delle classi dipendenti,
    // rendendo il codice più pulito e riducendo la dipendenza diretta tra le classi
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    public Order orderCreate(Order order) { //Crea un nuovo ordine utilizzando l'oggetto Order fornito come input.
        return orderRepository.save(order); //salva l'ordine utilizzando il repository
    }

    public Order orderCreateByUserId(Long idUser, Order order){
        User user = userRepository.findById(idUser).get(); //Trova e ottiene un utente dal repository basandosi sull'ID specificato.
        user.getOrders().add(order); //Aggiunge l'ordine fornito alla lista degli ordini dell'utente
        return orderRepository.save(order); //ed infine salva l'ordine
    }

    public List<Order> viewAllOrders() {
        return orderRepository.findAll();
    }

    public Order viewOrderById(Long idOrder) {
        return orderRepository.findById(idOrder).orElse(null);
    }


}
