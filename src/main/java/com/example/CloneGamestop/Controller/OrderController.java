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
public class OrderController {

    @Autowired //Utilizza il servizio OrderService per eseguire operazioni relative agli ordini.
    private OrderService orderService;

    @PostMapping("/order-create") //crea un ordine
    public ResponseEntity Order(@RequestBody Order order) {
        try { //Il metodo order() del servizio salva l'ordine. Se riuscito, ritorna HTTP 200 OK con l'ordine creato.
            return ResponseEntity.ok(orderService.orderCreate(order));
        }catch (Exception e) { //In caso di eccezione, ritorna HTTP 400 e il messaggio dell'eccezione come corpo
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/order/{idUser}") //crea un ordine
    public ResponseEntity orderByUserId(@PathVariable Long idUser, @RequestBody Order order) {
        try {// Restituisce una risposta HTTP 200 dopo aver creato un ordine per l'ID utente specificato utilizzando OrderService.
            return ResponseEntity.ok(orderService.orderCreateByUserId(idUser, order));
        }catch (Exception e) { // se non funzionante manda un messaggio di errore Http 400
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/view-all-order")
    public ResponseEntity<List<OrderDTO>> viewAllOrder() {
        List<Order> orderList = orderService.viewAllOrders();
        List<OrderDTO> orderDTOList = new ArrayList<>();
        for (Order order : orderList) {
            orderDTOList.add(OrderDTO.fromOrder(order));
        }
        return ResponseEntity.ok(orderDTOList);
    }

    @GetMapping(value = "/api/order/{idOrder}")
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

    @PutMapping(value = "/update-order/{idOrder}")
    public ResponseEntity modifyOrder(@PathVariable Long idOrder, @RequestBody Order order) {
        try {
            return ResponseEntity.ok(orderService.updateOrder(idOrder, order));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/delete-order/{idOrder}")
    public ResponseEntity<String> deleteOrderById(@PathVariable Long idOrder) {
        try {
            orderService.deleteOrderByIdOrder(idOrder);
            return ResponseEntity.ok("Product deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

}
