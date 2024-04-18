package com.example.CloneGamestop.DTO; // Pacchetto che contiene la classe ProductDTO

import com.example.CloneGamestop.Model.Product; // Importa la classe Product dal pacchetto Model
import jakarta.validation.constraints.NotBlank;
import lombok.Data; // Importa l'annotazione @Data di Lombok

@Data // Annotazione Lombok per generare automaticamente i getter, setter, toString, equals e hashCode
public class ProductDTO { // Dichiarazione della classe ProductDTO

    @NotBlank(message = "Il nome del prodotto non può essere vuoto")
    private String name; // Campo per il nome del prodotto

    private String description; // Campo per la descrizione del prodotto
    private int price; // Campo per il prezzo del prodotto

    public ProductDTO() {

    }

    // Metodo statico per creare un oggetto ProductDTO a partire da un oggetto Product
    public static ProductDTO fromProduct(Product product) {
        ProductDTO productDTO = new ProductDTO(); // Crea un nuovo oggetto ProductDTO
        productDTO.setName(product.getName()); // Imposta il nome del prodotto
        productDTO.setDescription(product.getDescription()); // Imposta la descrizione del prodotto
        productDTO.setPrice(product.getPrice()); // Imposta il prezzo del prodotto
        return productDTO; // Restituisce l'oggetto ProductDTO creato
    }
}
