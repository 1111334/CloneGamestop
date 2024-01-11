package com.example.CloneGamestop.Service;

import com.example.CloneGamestop.Model.Order;
import com.example.CloneGamestop.Model.Product;
import com.example.CloneGamestop.Model.User;
import com.example.CloneGamestop.Repository.OrderRepository;
import com.example.CloneGamestop.Repository.ProductRepository;
import com.example.CloneGamestop.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


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


    public Order updateOrder(Long idOrder, Order updateOrder) throws Exception {

        if (orderRepository.findById(idOrder).isPresent()) {

            Order order = orderRepository.findById(idOrder).get();

            if (Objects.nonNull(updateOrder.getStatusOrder())) {
                order.setStatusOrder(updateOrder.getStatusOrder());
            }

            if (Objects.nonNull(updateOrder.getAllActionsCompleted())) {
                order.setAllActionsCompleted(updateOrder.getAllActionsCompleted());
            }

            return orderRepository.save(order);
        } else {
            throw new Exception(String.format("User with ID %s not found", idOrder));
        }
    }
    public void deleteOrderByIdOrder(Long idOrder) {
        Optional<Order> optionalOrder = orderRepository.findById(idOrder);

        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();

            // Rimuovi l'ordine dalle associazioni con gli utenti nella tabella di join user_order
            for (User user : order.getUserSet()) {
                user.getOrders().remove(order);
            }

            // Rimuovi L'ordine dalle associazioni con i prodotti
            for (Product product : order.getProductSet()) {
                product.getOrders().remove(order);
            }


            // Infine, elimina effettivamente l'ordine
            orderRepository.delete(order);
        }
    }



}
