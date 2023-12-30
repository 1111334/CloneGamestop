package com.example.CloneGamestop.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "cart") // garantisce una corrispondenza diretta e chiara tra un'entità Java e una tabella specifica
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // genera id auto-incrementanti
    @Column(name = "id_cart") //  mappa la proprietà corrente dell'entità a una colonna chiamata id_cart nella tabella del database.
    private Long idCart;

    @OneToOne // un carrello per un singolo user
    @JoinColumn(name = "id_user", referencedColumnName = "id_user") // l'annotazione @JoinColumn specifica la colonna nel database (name = "id_user") che viene utilizzata per la relazione.
    private User user;

    @OneToMany(mappedBy = "cart") //un carrello per molti prodotti
    private List<Product> products;

    public Cart() {

    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Long getIdCart() {
        return idCart;
    }

    public void setIdCart(Long idCart) {
        this.idCart = idCart;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
