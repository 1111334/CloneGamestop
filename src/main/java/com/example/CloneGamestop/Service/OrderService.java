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

// Service per la gestione delle operazioni relative agli ordini
@Service
public class OrderService {

    // Iniezione dei repository necessari
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    // Metodo per creare un nuovo ordine
    public Order orderCreate(Order order) {
        return orderRepository.save(order);
    }

    // Metodo per creare un nuovo ordine associandolo a un utente tramite ID
    public Order orderCreateByUserId(Long idUser, Order order){
        User user = userRepository.findById(idUser).get();
        user.getOrders().add(order);
        return orderRepository.save(order);
    }

    // Metodo per visualizzare tutti gli ordini
    public List<Order> viewAllOrders() {
        return orderRepository.findAll();
    }

    // Metodo per visualizzare un ordine tramite ID
    public Order viewOrderById(Long idOrder) {
        return orderRepository.findById(idOrder).orElse(null);
    }

    // Metodo per aggiornare un ordine utilizzando l'ID dell'ordine e un oggetto Order
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

    // Metodo per eliminare un ordine utilizzando l'ID dell'ordine
    public void deleteOrderByIdOrder(Long idOrder) {
        Optional<Order> optionalOrder = orderRepository.findById(idOrder);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            for (User user : order.getUserSet()) {
                user.getOrders().remove(order);
            }
            for (Product product : order.getProductSet()) {
                product.getOrders().remove(order);
            }
            orderRepository.delete(order);
        }
    }
}
