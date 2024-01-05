package com.example.CloneGamestop.Controller;

import com.example.CloneGamestop.Model.Order;
import com.example.CloneGamestop.Model.Product;
import com.example.CloneGamestop.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

}
