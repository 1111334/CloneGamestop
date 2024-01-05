package com.example.CloneGamestop.Service;

import com.example.CloneGamestop.Model.Cart;
import com.example.CloneGamestop.Model.Order;
import com.example.CloneGamestop.Model.Product;
import com.example.CloneGamestop.Model.User;
import com.example.CloneGamestop.Repository.CartRepository;
import com.example.CloneGamestop.Repository.OrderRepository;
import com.example.CloneGamestop.Repository.ProductRepository;
import com.example.CloneGamestop.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;


    public Product productCreate(Product product) {
        return productRepository.save(product);
    }

    public Product productCreateByOrderId(Long idOrder, Product product) {
        Order order = orderRepository.findById(idOrder).get();
        order.getProductSet().add(product);
        return productRepository.save(product);
    }

    public Product productCreateByUserId(Long idUser, Product product) {
        User user = userRepository.findById(idUser).get();
        user.getProducts().add(product);
        return productRepository.save(product);
    }

    //@Transactional
    //public Product createProductAndAdd(Long idCart, Product product) throws Exception {
    //    Optional<Cart> optionalCart = cartRepository.findById(idCart);
    //    if (optionalCart.isPresent()) {
    //        Cart cart = optionalCart.get();
    //        cart.getProducts().add(product);
    //        product.setCart(cart);
    //        return productRepository.save(product);
    //    } else {
    //        throw new Exception("Cart with ID " + idCart + " not found");
    //    }
    //}

    public Product addProductToCart(Long idProduct, Long idCart) {
        // Recupera il prodotto dal database utilizzando l'ID
        Optional<Product> optionalProduct = productRepository.findById(idProduct);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();

            // Recupera il carrello dal database utilizzando l'ID
            Optional<Cart> optionalCart = cartRepository.findById(idCart);
            if (optionalCart.isPresent()) {
                Cart cart = optionalCart.get();

                // Associa il prodotto al carrello
                product.setCart(cart);
                cart.getProducts().add(product);

                // Salva il prodotto e il carrello nel database
                productRepository.save(product);
                cartRepository.save(cart);

                return product;
            } else {
                throw new RuntimeException("Cart not found with ID: " + idCart);
            }
        } else {
            throw new RuntimeException("Product not found with ID: " + idProduct);
        }

    }
}
