package com.example.CloneGamestop.Model; // Pacchetto che contiene la classe Product

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data // Annotazione Lombok per generare automaticamente i getter, setter, toString, equals e hashCode
//@NoArgsConstructor // Annotazione Lombok per generare un costruttore vuoto
// genera automaticamente un costruttore che accetta tutti i campi della classe Product
@AllArgsConstructor
@NoArgsConstructor
@Entity // Indica che questa classe è un'entità JPA
@Table(name = "product") // Specifica il nome della tabella nel database
public class Product { // Dichiarazione della classe Product

    @Id // Indica che questo campo è la chiave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera valori per questa colonna utilizzando l'identità del database
    @Column(name = "id_product") // Mappa la proprietà corrente dell'entità a una colonna chiamata id_product nella tabella del database
    private Long idProduct; // Campo per l'identificatore del prodotto

    private String name; // Campo per il nome del prodotto
    private String description; // Campo per la descrizione del prodotto
    private int price; // Campo per il prezzo del prodotto
    private int quantityAvailable; // Campo per la quantità disponibile del prodotto

    @ManyToOne(cascade = CascadeType.ALL) // Definisce una relazione molti-a-uno con la classe Cart
    @JoinColumn(name = "id_cart") // Specifica la colonna nel database che fa riferimento al carrello
    @JsonBackReference // Ignora questa proprietà durante la serializzazione JSON per evitare loop infiniti
    private Cart cart; // Campo per il carrello associato al prodotto

    @ManyToMany(mappedBy = "products", cascade = CascadeType.ALL) // Definisce una relazione molti-a-molti con la classe User
    private Set<User> users = new HashSet<>(); // Campo per il set di utenti associati al prodotto

    @ManyToMany(mappedBy = "products", cascade = CascadeType.ALL) // Definisce una relazione molti-a-molti con la classe Order
    private Set<Order> orders = new HashSet<>(); // Campo per il set di ordini associati al prodotto
}
