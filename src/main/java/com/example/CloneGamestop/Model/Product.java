package com.example.CloneGamestop.Model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "product") // garantisce una corrispondenza diretta e chiara tra un'entità Java e una tabella specifica
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // genera id auto-incrementanti
    @Column(name = "id_product") //  mappa la proprietà corrente dell'entità a una colonna chiamata id_user nella tabella del database.
    private Long idProduct;
    private String name;
    private String description;
    private int price;
    private int quantityAvailable;
    
    public Product() {
        
    }

    @ManyToOne //molti prodotti per un carrello
    @JoinColumn(name = "id_cart") // l'annotazione @JoinColumn specifica la colonna nel database (name = "id_user") che viene utilizzata per la relazione.
    private Cart cart;

    @ManyToMany(mappedBy = "products")
    private Set<User> users = new HashSet<>();

    @ManyToMany(mappedBy = "products")
    private Set<Order> orders = new HashSet<>();


    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Long getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(Long idProduct) {
        this.idProduct = idProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
