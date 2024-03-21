package com.example.CloneGamestop.Model;

import com.example.CloneGamestop.TestController.Role;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

// Annotation di Lombok per generare automaticamente getter, setter, equals, hashCode, toString
@Data
// Annotation di Lombok per generare un costruttore vuoto
@NoArgsConstructor
// Annotation per definire questa classe come entità persistente
@Entity
// Annotation per specificare il nome della tabella nel database
@Table(name = "users")
public class User /*implements UserDetails*/ {
    // Annotation per specificare che questo campo è la chiave primaria e generato automaticamente
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long idUser;

    // Campi per lo username, la password e l'email dell'utente
    private String username;
    private String surname;
    private String password;

    @Column(unique = true)
    private String email;

    private boolean isActive;
    @Column(length = 36)
    private String activationCode;

    private LocalDateTime jwtCreatedOn;

    // Relazione uno a uno con il carrello dell'utente
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Cart cart;

    // Relazione molti a molti con gli ordini dell'utente
    @ManyToMany
    @JoinTable(
            name = "user_order",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id")
    )
    private Set<Order> orders = new HashSet<>();

    // Relazione molti a molti con i prodotti associati all'utente
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_product",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Product> products = new HashSet<>();

    // Relazione molti a molti con i ruoli dell'utente
    @ManyToMany
    private Set<Role> roles = new HashSet<>();


}
