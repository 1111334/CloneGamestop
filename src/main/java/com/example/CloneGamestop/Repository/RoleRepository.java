package com.example.CloneGamestop.Repository;

import com.example.CloneGamestop.TestController.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
