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


import java.util.List;
import java.util.Objects;
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
        return productRepository.save(product); //salva il prodotto creato
    }

    public Product productCreateByOrderIdAndByUserId(Long idOrder, Long idUser, Product product) {
        Order order = orderRepository.findById(idOrder).get();
        order.getProductSet().add(product);

        User user = userRepository.findById(idUser).get();
        user.getProducts().add(product);

        return productRepository.save(product);
    }

    public Product addProductToCart(Long idProduct, Long idCart) {
        // Recupera il prodotto dal database utilizzando l'ID
        Optional<Product> optionalProduct = productRepository.findById(idProduct);
        if (optionalProduct.isPresent()) { //controlla se esista o meno il prodotto
            Product product = optionalProduct.get();

            // Recupera il carrello dal database utilizzando l'ID
            Optional<Cart> optionalCart = cartRepository.findById(idCart);
            if (optionalCart.isPresent()) { //controlla se esista o meno il carrello
                Cart cart = optionalCart.get();

                // Associa il prodotto al carrello
                product.setCart(cart);
                cart.getProducts().add(product);

                // Salva il prodotto e il carrello nel database
                productRepository.save(product);
                cartRepository.save(cart);

                return product;
            } else { // Lancia un'eccezione con un messaggio specifico se il carrello o il prodotto non sono trovati per l'ID fornito.
                throw new RuntimeException("Cart not found with ID: " + idCart);
            }
        } else {
            throw new RuntimeException("Product not found with ID: " + idProduct);
        }
    }

    public List<Product> viewListProduct() {
        return productRepository.findAll();
    }

    public Product viewProductDTOById(Long idProduct) {
        return productRepository.findById(idProduct).orElse(null);
    }

    public Product updatedProduct(Long idProduct, Product updateProduct) throws Exception {

        if (productRepository.findById(idProduct).isPresent()) {

            Product product = productRepository.findById(idProduct).get();

            if (Objects.nonNull(updateProduct.getName())) {
                product.setName(updateProduct.getName());
            }

            if (Objects.nonNull(updateProduct.getDescription())) {
                product.setDescription(updateProduct.getDescription());
            }

            if (Objects.nonNull(updateProduct.getPrice())) {
                product.setPrice(updateProduct.getPrice());
            }

            if (Objects.nonNull(updateProduct.getQuantityAvailable())) {
                product.setQuantityAvailable(updateProduct.getQuantityAvailable());
            }

            return productRepository.save(product);
        } else {
            throw new Exception(String.format("Product with ID %s not found", idProduct));
        }
    }

    public void deleteProductByIdProduct(Long idProduct) {
        Optional<Product> optionalProduct = productRepository.findById(idProduct);

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();

            // Rimuovi il prodotto dalle associazioni con gli utenti nella tabella di join user_product
            for (User user : product.getUsers()) {
                user.getProducts().remove(product);
            }

            // Rimuovi il prodotto dalle associazioni con gli ordini
            for (Order order : product.getOrders()) {
                order.getProductSet().remove(product);
            }

            // Rimuovi il prodotto dal carrello, se presente
            if (product.getCart() != null) {
                product.getCart().getProducts().remove(product);
            }

            // Infine, elimina effettivamente il prodotto
            productRepository.delete(product);
        }
    }



}
