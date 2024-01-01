package com.example.CloneGamestop.Service;

import com.example.CloneGamestop.Model.Order;
import com.example.CloneGamestop.Model.Product;
import com.example.CloneGamestop.Model.User;
import com.example.CloneGamestop.Repository.OrderRepository;
import com.example.CloneGamestop.Repository.ProductRepository;
import com.example.CloneGamestop.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;


    public Product productCreate(Product product) {
        return productRepository.save(product);
    }

    public Product productCreateByOrderId(Long idOrder, Product product){
        Order order = orderRepository.findById(idOrder).get();
        order.getProductSet().add(product);
        return productRepository.save(product);
    }

    public Product productCreateByUserId(Long idUser, Product product){
        User user = userRepository.findById(idUser).get();
        user.getProducts().add(product);
        return productRepository.save(product);
    }
}
