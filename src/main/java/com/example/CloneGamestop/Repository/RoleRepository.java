package com.example.CloneGamestop.Repository;

import com.example.CloneGamestop.TestController.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Repository per gestire l'accesso ai dati relativi all'entità Role
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
