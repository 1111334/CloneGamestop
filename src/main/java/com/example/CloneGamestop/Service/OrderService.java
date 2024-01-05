package com.example.CloneGamestop.Service;

import com.example.CloneGamestop.Model.Order;
import com.example.CloneGamestop.Model.User;
import com.example.CloneGamestop.Repository.OrderRepository;
import com.example.CloneGamestop.Repository.ProductRepository;
import com.example.CloneGamestop.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    public Order orderCreate(Order order) {
        return orderRepository.save(order);
    }

    public Order orderCreateByUserId(Long idUser, Order order){
        User user = userRepository.findById(idUser).get();
        user.getOrders().add(order);
        return orderRepository.save(order);
    }


}
