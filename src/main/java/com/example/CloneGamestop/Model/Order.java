package com.example.CloneGamestop.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "order_table") //nome della tabella
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // genera id auto-incrementanti
    private Long idOrder;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm") // formato della data
    private LocalDateTime dateOrder; // mappa la proprietà corrente dell'entità a una colonna
    private boolean allActionsCompleted;
    private String statusOrder;

    public Order() {

    }

    @PrePersist //per inserire l'ora e la data in automatico
    public void prePersist() {
        this.dateOrder = LocalDateTime.now();
    }
    @ManyToMany(mappedBy = "orders") //un ordine per molti user
    private Set<User> userSet = new HashSet<>();

    @ManyToMany // relazione; molti ordini per molti prodotti
    @JoinTable(
            name = "order_product", //nome della tabella di join
            joinColumns = @JoinColumn(name = "order_id"),  //nome della colonna che fa riferimento all'entità Order
            inverseJoinColumns = @JoinColumn(name = "product_id")  //nome della colonna che fa riferimento all'entità Product
    )
    private Set<Product> products = new HashSet<>();

    public Long getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Long idOrder) {
        this.idOrder = idOrder;
    }

    public LocalDateTime getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(LocalDateTime dateOrder) {
        this.dateOrder = dateOrder;
    }

    public String getStatusOrder() {
        return statusOrder;
    }

    public void setStatusOrder(String statusOrder) {
        this.statusOrder = statusOrder;
    }

    //Da rivedere
    public boolean getTutteLeAzioniCompletate() {
        return allActionsCompleted;
    }

    public void setTutteLeAzioniCompletate(boolean allActionsCompleted) {
        this.allActionsCompleted = allActionsCompleted;
    }

    public Set<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;
    }

    public Set<Product> getProductSet() {
        return products;
    }

    public void setProductSet(Set<Product> products) {
        this.products = products;
    }

    //Da rivedere
    public void  statusOrderIsCompleteOrNotComplete() {
        if (allActionsCompleted) {
            this.statusOrder = "completato";
        } else {
            this.statusOrder = "in attesa";
        }
        Order orders = new Order();
        orders.statusOrderIsCompleteOrNotComplete();
    }

}

