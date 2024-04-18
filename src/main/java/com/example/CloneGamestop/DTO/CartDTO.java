package com.example.CloneGamestop.DTO; // Package che contiene la classe CartDTO

import com.example.CloneGamestop.Model.Cart;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data // Annotazione Lombok per generare automaticamente i getter, setter, toString, equals e hashCode
public class CartDTO { // Dichiarazione della classe CartDTO

    private Long idCart; // Campo per l'ID del carrello

    @NotBlank(message = "L'indirizzo di spedizione non può essere vuoto")
    private String shippingAddress; // Campo per l'indirizzo di spedizione

    @NotNull(message = "La data di aggiunta non può essere nulla")
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
