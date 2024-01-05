package com.example.CloneGamestop.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "cart") // garantisce una corrispondenza diretta e chiara tra un'entità Java e una tabella specifica
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // genera id auto-incrementanti
    @Column(name = "id_cart") // mappa la proprietà corrente dell'entità a una colonna chiamata id_cart nella tabella del database.
    private Long idCart;
    private String shippingAddress;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm") //formato della data
    private LocalDateTime dateOfAddition;

    @PrePersist //Metodo @PrePersist: imposta la data di aggiunta alla data e ora correnti prima del salvataggio.
    public void prePersist() {
        this.dateOfAddition = LocalDateTime.now();
    }

    @JsonBackReference
    @OneToOne //relazione: un carrello per user
    @JoinColumn(name = "id_user",referencedColumnName = "id_user") // l'annotazione @JoinColumn specifica la colonna nel database (name = "id_user") che viene utilizzata per la relazione.
    private User user;

    @OneToMany(mappedBy = "cart") //relazione: un carrello per molti prodotti
    //@JsonManagedReference
    private List<Product> products;

    public Cart() {

    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public LocalDateTime getDateOfAddition() {
        return dateOfAddition;
    }

    public void setDateOfAddition(LocalDateTime dateOfAddition) {
        this.dateOfAddition = dateOfAddition;
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
