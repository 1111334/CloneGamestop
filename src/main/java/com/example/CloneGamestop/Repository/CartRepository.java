package com.example.CloneGamestop.Repository;

import com.example.CloneGamestop.Model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Repository per gestire l'accesso ai dati relativi all'entità Cart
@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
}
