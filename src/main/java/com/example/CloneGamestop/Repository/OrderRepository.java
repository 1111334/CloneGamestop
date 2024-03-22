package com.example.CloneGamestop.Repository;

import com.example.CloneGamestop.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Repository per gestire l'accesso ai dati relativi all'entità Order
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
