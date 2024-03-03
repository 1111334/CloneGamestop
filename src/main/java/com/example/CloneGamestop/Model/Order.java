package com.example.CloneGamestop.Model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
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

    @PrePersist //per inserire l'ora e la data in automatico
    public void prePersist() {
        this.dateOrder = LocalDateTime.now();
    }

    @ManyToMany(mappedBy = "orders") //un ordine per molti user
    private Set<User> userSet = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL) // relazione; molti ordini per molti prodotti
    @JoinTable(
            name = "order_product", //nome della tabella di join
            joinColumns = @JoinColumn(name = "order_id"),  //nome della colonna che fa riferimento all'entità Order
            inverseJoinColumns = @JoinColumn(name = "product_id")  //nome della colonna che fa riferimento all'entità Product
    )
    private Set<Product> products = new HashSet<>();

    //Da rivedere
    public void statusOrderIsCompleteOrNotComplete() {
        if (allActionsCompleted) {
            this.statusOrder = "completato";
        } else {
            this.statusOrder = "in attesa";
        }
        Order orders = new Order();
        orders.statusOrderIsCompleteOrNotComplete();
    }

    public boolean getAllActionsCompleted() {
        return allActionsCompleted;
    }

    public Set<Product> getProductSet() {
        return this.products;
    }
}

