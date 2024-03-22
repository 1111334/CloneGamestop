package com.example.CloneGamestop.Repository;

import com.example.CloneGamestop.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// Repository per gestire l'accesso ai dati relativi all'entità User
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Metodo per trovare uno user tramite l'email
    Optional<User> findByEmail(String email);

    // Metodo per trovare uno user tramite il codice di attivazione
    User findByActivationCode(String activationCode);
}
