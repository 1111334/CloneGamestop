package com.example.CloneGamestop.Model; // Pacchetto che contiene la classe Order

import com.fasterxml.jackson.annotation.JsonFormat; // Importa l'annotazione JsonFormat da Jackson
import jakarta.persistence.*; // Importa le annotazioni di JPA
import lombok.Data; // Importa l'annotazione @Data di Lombok
import lombok.NoArgsConstructor; // Importa l'annotazione @NoArgsConstructor di Lombok

import java.time.LocalDateTime; // Importa la classe LocalDateTime
import java.util.HashSet; // Importa la classe HashSet
import java.util.Set; // Importa la classe Set

@Data // Annotazione Lombok per generare automaticamente i getter, setter, toString, equals e hashCode
@NoArgsConstructor // Annotazione Lombok per generare un costruttore vuoto
@Entity // Indica che questa classe è un'entità JPA
@Table(name = "order_table") // Specifica il nome della tabella nel database
public class Order { // Dichiarazione della classe Order

    @Id // Indica che questo campo è la chiave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera valori per questa colonna utilizzando l'identità del database
    private Long idOrder; // Campo per l'identificatore dell'ordine

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm") // Specifica il formato per la rappresentazione JSON della data e dell'ora
    private LocalDateTime dateOrder; // Campo per la data dell'ordine

    private boolean allActionsCompleted; // Campo che indica se tutte le azioni sono state completate
    private String statusOrder; // Campo per lo stato dell'ordine

    @PrePersist // Indica che il metodo prePersist() deve essere eseguito prima di persistere l'entità nel database
    public void prePersist() {
        this.dateOrder = LocalDateTime.now(); // Imposta la data dell'ordine alla data e ora correnti
    }

    @ManyToMany(mappedBy = "orders") // Definisce una relazione molti-a-molti con la classe User
    private Set<User> userSet = new HashSet<>(); // Campo per il set di utenti associati all'ordine

    @ManyToMany(cascade = CascadeType.ALL) // Definisce una relazione molti-a-molti con la classe Product
    @JoinTable( // Specifica la tabella di join per la relazione molti-a-molti
            name = "order_product", // Nome della tabella di join nel database
            joinColumns = @JoinColumn(name = "order_id"), // Colonna nel database che fa riferimento all'ordine
            inverseJoinColumns = @JoinColumn(name = "product_id") // Colonna nel database che fa riferimento al prodotto
    )
    private Set<Product> products = new HashSet<>(); // Campo per il set di prodotti associati all'ordine

    // Metodo per impostare lo stato dell'ordine in base al completamento delle azioni
    public void statusOrderIsCompleteOrNotComplete() {
        if (allActionsCompleted) {
            this.statusOrder = "completato";
        } else {
            this.statusOrder = "in attesa";
        }
    }

    // Metodo getter per il campo allActionsCompleted
    public boolean getAllActionsCompleted() {
        return allActionsCompleted;
    }

    // Metodo getter per il set di prodotti associati all'ordine
    public Set<Product> getProductSet() {
        return this.products;
    }
}
