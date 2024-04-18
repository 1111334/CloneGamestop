package com.example.CloneGamestop.Controller;

import com.example.CloneGamestop.DTO.OrderDTO;
import com.example.CloneGamestop.Model.Order;
import com.example.CloneGamestop.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
// Indica che questa classe è un controller REST e restituisce risposte JSON
public class OrderController {

    @Autowired
    private OrderService orderService; // Utilizza il servizio OrderService per eseguire operazioni relative agli ordini

    @PostMapping("/order-create")
    // Metodo per gestire la creazione di un ordine
    public ResponseEntity Order(@RequestBody Order order) {
        // Il metodo orderCreate() del servizio salva l'ordine. Se riuscito, ritorna HTTP 200 OK con l'ordine creato
        return ResponseEntity.ok(orderService.orderCreate(order));
    }

    @PostMapping("/order/{idUser}")
    // Metodo per creare un ordine per un determinato ID utente
    public ResponseEntity orderByUserId(@PathVariable Long idUser, @RequestBody Order order) {
        // Crea un ordine per l'ID utente specificato utilizzando OrderService
        return ResponseEntity.ok(orderService.orderCreateByUserId(idUser, order));
    }

    @GetMapping(value = "/api/order/{idOrder}")
    // Ottiene un ordine tramite ID e restituisce un DTO dell'ordine
    public ResponseEntity<OrderDTO> getUserById(@PathVariable Long idOrder) {
        Order order = orderService.viewOrderById(idOrder);

        if (order != null) {
            // Se l'ordine è stato trovato, convertirlo in un DTO e restituirlo nella risposta
            OrderDTO orderDTO = OrderDTO.fromOrder(order);
            return new ResponseEntity<>(orderDTO, HttpStatus.OK);
        } else {
            // Se l'ordine non è stato trovato, restituire una risposta 404 Not Found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/view-all-order")
    // Ottiene tutti gli ordini e restituisce una lista di DTO degli ordini
    public ResponseEntity<List<OrderDTO>> viewAllOrder() {
        List<Order> orderList = orderService.viewAllOrders();
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for (Order order : orderList) {
            orderDTOList.add(OrderDTO.fromOrder(order)); // Converti ogni ordine in DTO e aggiungilo alla lista
        }
        return ResponseEntity.ok(orderDTOList); // Restituisce la lista di DTO degli ordini
    }

    @PutMapping(value = "/update-order/{idOrder}")
    // Metodo per modificare un ordine esistente
    public ResponseEntity modifyOrder(@PathVariable Long idOrder, @RequestBody Order order) throws Exception {
        // Modifica l'ordine e restituisce una risposta OK
        return ResponseEntity.ok(orderService.updateOrder(idOrder, order));
    }

    @DeleteMapping(value = "/delete-order/{idOrder}")
    // Metodo per eliminare un ordine esistente
    public ResponseEntity<String> deleteOrderById(@PathVariable Long idOrder) {

        // Elimina l'ordine utilizzando OrderService
        orderService.deleteOrderByIdOrder(idOrder);
        return ResponseEntity.ok("Ordine eliminato con successo");
    }
}
