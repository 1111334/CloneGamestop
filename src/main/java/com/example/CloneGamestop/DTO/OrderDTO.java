package com.example.CloneGamestop.DTO; // Pacchetto che contiene la classe OrderDTO

import com.example.CloneGamestop.Model.Order; // Importa la classe Order dal pacchetto Model
import lombok.Data; // Importa l'annotazione @Data di Lombok

@Data // Annotazione Lombok per generare automaticamente i getter, setter, toString, equals e hashCode
public class OrderDTO { // Dichiarazione della classe OrderDTO

    private Long idOrder; // Campo per l'ID dell'ordine
    private boolean allActionsCompleted; // Campo per indicare se tutte le azioni sono state completate
    private String statusOrder; // Campo per lo stato dell'ordine

    // Costruttore vuoto
    public OrderDTO() {
    }

    // Metodo statico per creare un oggetto OrderDTO a partire da un oggetto Order
    public static OrderDTO fromOrder(Order order) {
        OrderDTO orderDTO = new OrderDTO(); // Crea un nuovo oggetto OrderDTO
        orderDTO.setIdOrder(orderDTO.idOrder); // Imposta l'ID dell'ordine
        orderDTO.setallActionsCompleted(orderDTO.allActionsCompleted); // Imposta il flag per indicare se tutte le azioni sono state completate
        orderDTO.setStatusOrder(orderDTO.statusOrder); // Imposta lo stato dell'ordine
        return orderDTO; // Restituisce l'oggetto OrderDTO creato
    }

    // Metodo privato per impostare il flag allActionsCompleted
    private void setallActionsCompleted(boolean allActionsCompleted) {
        this.allActionsCompleted = allActionsCompleted;
    }

}
