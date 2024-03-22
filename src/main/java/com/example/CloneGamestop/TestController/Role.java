package com.example.CloneGamestop.TestController;

import jakarta.persistence.*;

// Entità per la gestione dei ruoli utente
@Entity
@Table(name = "auth_roles")
public class Role {
    // Identificatore univoco del ruolo
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRole;

    // Tipo del ruolo (ad esempio, ADMIN, USER, etc.)
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    // Metodo getter per l'ID del ruolo
    public Long getIdRole() {
        return idRole;
    }

    // Metodo setter per l'ID del ruolo
    public void setIdRole(Long idRole) {
        this.idRole = idRole;
    }

    // Metodo getter per il tipo del ruolo
    public RoleType getRoleType() {
        return roleType;
    }

    // Metodo setter per il tipo del ruolo
    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }
}


//Ruoli --> iniziare lezioni riguardo i ruoli subito dopo gestione di un sistema login
