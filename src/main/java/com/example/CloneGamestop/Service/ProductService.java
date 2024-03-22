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

// Service per la gestione delle operazioni relative ai prodotti
@Service
public class ProductService {

    // Iniezione dei repository necessari
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    // Metodo per creare un nuovo prodotto
    public Product productCreate(Product product) {
        return productRepository.save(product);
    }

    // Metodo per creare un nuovo prodotto associandolo a un ordine e a un utente tramite i rispettivi ID
    public Product productCreateByOrderIdAndByUserId(Long idOrder, Long idUser, Product product) {
        Order order = orderRepository.findById(idOrder).get();
        order.getProductSet().add(product);

        User user = userRepository.findById(idUser).get();
        user.getProducts().add(product);

        return productRepository.save(product);
    }

    // Metodo per aggiungere un prodotto a un carrello utilizzando gli ID del prodotto e del carrello
    public Product addProductToCart(Long idProduct, Long idCart) {
        Optional<Product> optionalProduct = productRepository.findById(idProduct);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();

            Optional<Cart> optionalCart = cartRepository.findById(idCart);
            if (optionalCart.isPresent()) {
                Cart cart = optionalCart.get();
                product.setCart(cart);
                cart.getProducts().add(product);

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

    // Metodo per visualizzare tutti i prodotti
    public List<Product> viewListProduct() {
        return productRepository.findAll();
    }

    // Metodo per visualizzare un prodotto tramite ID
    public Product viewProductDTOById(Long idProduct) {
        return productRepository.findById(idProduct).orElse(null);
    }

    // Metodo per aggiornare un prodotto utilizzando l'ID del prodotto e un oggetto Product
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

    // Metodo per eliminare un prodotto utilizzando l'ID del prodotto
    public void deleteProductByIdProduct(Long idProduct) {
        Optional<Product> optionalProduct = productRepository.findById(idProduct);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            for (User user : product.getUsers()) {
                user.getProducts().remove(product);
            }
            for (Order order : product.getOrders()) {
                order.getProductSet().remove(product);
            }
            if (product.getCart() != null) {
                product.getCart().getProducts().remove(product);
            }
            productRepository.delete(product);
        }
    }
}
