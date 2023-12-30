package com.example.CloneGamestop.Model;

import com.example.CloneGamestop.Constants.Role;
import jakarta.persistence.*;


@Entity
@Table(name = "users") // garantisce una corrispondenza diretta e chiara tra un'entità Java e una tabella specifica
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // genera id auto-incrementanti
    @Column(name = "id_user") //  mappa la proprietà corrente dell'entità a una colonna chiamata id_user nella tabella del database.
    private Long idUser;
    private String username;
    private String password;
    private String email;

    // Aggiunta della proprietà per il ruolo
    @Enumerated(EnumType.STRING)
    @Column(name = "role") //  mappa la proprietà corrente dell'entità a una colonna chiamata role nella tabella del database.
    private Role role;

    //Qualsiasi operazione di gestione della relazione (come l'aggiunta, la rimozione o la modifica della relazione) tra User e Carrello sarà gestita automaticamente in base alle operazioni eseguite sull'entità User.
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL) //indica il tipo di relazione tra le entità mappedBy = "user" indica che l'entità Cart contiene il mapping effettivo
                                 // della relazione attraverso un campo o una proprietà chiamata user.
    private Cart cart;

    public User() {

    }


    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
