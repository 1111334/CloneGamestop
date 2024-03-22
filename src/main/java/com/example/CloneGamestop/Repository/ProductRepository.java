package com.example.CloneGamestop.Repository;

import com.example.CloneGamestop.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Repository per gestire l'accesso ai dati relativi all'entità Product
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {


}
