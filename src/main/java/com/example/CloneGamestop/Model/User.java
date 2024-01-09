package com.example.CloneGamestop.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "users") // garantisce una corrispondenza diretta e chiara tra un'entità Java e una tabella specifica
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // genera id auto-incrementanti
    @Column(name = "id_user")
    //  mappa la proprietà corrente dell'entità a una colonna chiamata id_user nella tabella del database.
    private Long idUser;
    private String username;
    private String password;
    private String email;

    // Da anallizzare meglio
    // Aggiunta della proprietà per il ruolo
   // @Enumerated(EnumType.STRING)
    //@Column(name = "role")
    //  mappa la proprietà corrente dell'entità a una colonna chiamata role nella tabella del database.
   // private Role role;

    //Qualsiasi operazione di gestione della relazione (come l'aggiunta, la rimozione o la modifica della relazione) tra User e Carrello sarà gestita automaticamente in base alle operazioni eseguite sull'entità User.

    @JsonManagedReference
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)  //indica il tipo di relazione tra le entità mappedBy = "user" indica che l'entità Cart contiene il mapping effettivo
    @JoinColumn(name = "id_user") //Specifica la colonna nel database che rappresenta la relazione tra entità tramite un ID utente.
    private Cart cart;

    @ManyToMany
    @JoinTable(
            name = "user_order",  // nome della tabella di join nel database
            joinColumns = @JoinColumn(name = "user_id"),  // nome della colonna che fa riferimento all'entità User
            inverseJoinColumns = @JoinColumn(name = "order_id")  // nome della colonna che fa riferimento all'entità Order
    )
    private Set<Order> orders = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "user_product", // nome della tabella di join
            joinColumns = @JoinColumn(name = "user_id"),  // nome della colonna che fa riferimento all'entità User
            inverseJoinColumns = @JoinColumn(name = "product_id")  // nome della colonna che fa riferimento all'entità Product
    )
    private Set<Product> products = new HashSet<>();

    public User() {

    }


    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Da anallizzare meglio
    /*public Role getRole() {
        return role;
    }

    //public void setRole(Role role) {
        this.role = role;
    }*/

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
