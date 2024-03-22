package com.example.CloneGamestop.DTO; // Package che contiene la classe CartDTO

import com.example.CloneGamestop.Model.Cart; // Importa la classe Cart dal package Model
import lombok.Data; // Importa l'annotazione @Data di Lombok

import java.time.LocalDateTime; // Importa la classe LocalDateTime

@Data // Annotazione Lombok per generare automaticamente i getter, setter, toString, equals e hashCode
public class CartDTO { // Dichiarazione della classe CartDTO

    private Long idCart; // Campo per l'ID del carrello
    private String shippingAddress; // Campo per l'indirizzo di spedizione
    private LocalDateTime dateOfAddition; // Campo per la data di aggiunta

    // Metodo statico per creare un oggetto CartDTO a partire da un oggetto Cart
    public static CartDTO fromCart(Cart cart) {
        CartDTO cartDTO = new CartDTO(); // Crea un nuovo oggetto CartDTO
        cartDTO.setIdCart(cart.getIdCart()); // Imposta l'ID del carrello
        cartDTO.setDateOfAddition(cart.getDateOfAddition()); // Imposta la data di aggiunta
        cartDTO.setShippingAddress(cart.getShippingAddress()); // Imposta l'indirizzo di spedizione
        return cartDTO; // Restituisce l'oggetto CartDTO creato
    }
}
