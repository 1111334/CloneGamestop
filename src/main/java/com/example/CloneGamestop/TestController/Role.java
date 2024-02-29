package com.example.CloneGamestop.TestController;

import jakarta.persistence.*;
@Entity
@Table(name = "auth_roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRole;
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    public Long getIdRole() {
        return idRole;
    }

    public void setIdRole(Long idRole) {
        this.idRole = idRole;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }
}