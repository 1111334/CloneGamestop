package com.example.CloneGamestop.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "product") //garantisce una corrispondenza diretta e chiara tra un'entità Java e una tabella specifica
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //genera id auto-incrementanti
    @Column(name = "id_product") //mappa la proprietà corrente dell'entità a una colonna chiamata id_user nella tabella del database.
    private Long idProduct;
    private String name;
    private String description;
    private int price;
    private int quantityAvailable;

    @ManyToOne(cascade = CascadeType.ALL) //molti prodotti per un carrello
    @JoinColumn(name = "id_cart") // l'annotazione @JoinColumn specifica la colonna nel database (name = "id_cart") che viene utilizzata per la relazione.
    @JsonBackReference
    private Cart cart;

    @ManyToMany(mappedBy = "products", cascade = CascadeType.ALL) //molti prodotti per molti user
    private Set<User> users = new HashSet<>();

    @ManyToMany(mappedBy = "products", cascade = CascadeType.ALL) //molti prodotti per molti ordini
    private Set<Order> orders = new HashSet<>();

}
