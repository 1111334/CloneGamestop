package com.example.CloneGamestop.Model; // Pacchetto che contiene la classe Cart

import com.fasterxml.jackson.annotation.JsonFormat; // Importa l'annotazione JsonFormat da Jackson
import com.fasterxml.jackson.annotation.JsonManagedReference; // Importa l'annotazione JsonManagedReference da Jackson
import jakarta.persistence.*; // Importa le annotazioni di JPA
import lombok.Data; // Importa l'annotazione @Data di Lombok
import lombok.NoArgsConstructor; // Importa l'annotazione @NoArgsConstructor di Lombok

import java.time.LocalDateTime; // Importa la classe LocalDateTime
import java.util.List; // Importa la classe List

@Data // Annotazione Lombok per generare automaticamente i getter, setter, toString, equals e hashCode
@NoArgsConstructor // Annotazione Lombok per generare un costruttore vuoto
@Entity // Indica che questa classe è un'entità JPA
@Table(name = "cart") // Specifica il nome della tabella nel database
public class Cart { // Dichiarazione della classe Cart

    @Id // Indica che questo campo è la chiave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Genera valori per questa colonna utilizzando l'identità del database
    @Column(name = "id_cart") // Mappa questa proprietà alla colonna id_cart nella tabella del database
    private Long idCart; // Campo per l'identificatore del carrello

    private String shippingAddress; // Campo per l'indirizzo di spedizione

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm") // Specifica il formato per la rappresentazione JSON della data e dell'ora
    private LocalDateTime dateOfAddition; // Campo per la data di aggiunta

    @PrePersist // Indica che il metodo prePersist() deve essere eseguito prima di persistere l'entità nel database
    public void prePersist() {
        this.dateOfAddition = LocalDateTime.now(); // Imposta la data di aggiunta alla data e ora correnti
    }

    @OneToOne(cascade = CascadeType.ALL) // Definisce una relazione uno-a-uno con la classe User
    @JoinColumn(name = "id_user", referencedColumnName = "id_user") // Specifica la colonna nel database utilizzata per la relazione
    private User user; // Campo per l'utente associato al carrello

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL) // Definisce una relazione uno-a-molti con la classe Product
    @JsonManagedReference // Indica che questa proprietà gestisce la parte "referenziante" della relazione
    private List<Product> products; // Campo per la lista dei prodotti nel carrello
}
