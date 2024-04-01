package com.example.CloneGamestop.Model; // Pacchetto che contiene la classe User

import com.example.CloneGamestop.TestController.Role; // Importa la classe Role
import jakarta.persistence.*; // Importa le annotazioni di JPA
import lombok.Data; // Importa l'annotazione @Data di Lombok
import lombok.NoArgsConstructor; // Importa l'annotazione @NoArgsConstructor di Lombok

import java.time.LocalDateTime; // Importa la classe LocalDateTime
import java.util.HashSet; // Importa la classe HashSet
import java.util.Set; // Importa la classe Set

// Annotation di Lombok per generare automaticamente getter, setter, equals, hashCode, toString
@Data
// Annotation di Lombok per generare un costruttore vuoto
@NoArgsConstructor
// Annotation per definire questa classe come entità persistente
@Entity
// Annotation per specificare il nome della tabella nel database
@Table(name = "users")
public class User { // Dichiarazione della classe User

    // Annotation per specificare che questo campo è la chiave primaria e generato automaticamente
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long idUser; // Campo per l'identificatore dell'utente

    // Campi per lo username, il cognome, la password e l'email dell'utente
    private String username;
    private String surname;
    private String password;
    @Column(unique = true)
    private String email;

    private boolean isActive; // Campo che indica se l'utente è attivo

    @Column(length = 36)
    private String activationCode; // Codice di attivazione dell'utente

    @Column(length = 36)
    private String passwordResetCode;

    private LocalDateTime jwtCreatedOn; // Data e ora di creazione del JWT

    // Relazione uno a uno con il carrello dell'utente
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Cart cart; // Campo per il carrello dell'utente

    // Relazione molti a molti con gli ordini dell'utente
    @ManyToMany
    @JoinTable(
            name = "user_order",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id")
    )
    private Set<Order> orders = new HashSet<>(); // Campo per il set di ordini associati all'utente

    // Relazione molti a molti con i prodotti associati all'utente
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_product",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Product> products = new HashSet<>(); // Campo per il set di prodotti associati all'utente

    // Relazione molti a molti con i ruoli dell'utente
    @ManyToMany
    private Set<Role> roles = new HashSet<>(); // Campo per il set di ruoli associati all'utente
}
