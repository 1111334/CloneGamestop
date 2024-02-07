package com.example.CloneGamestop.Security;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public interface UserDetails {

    // Implementazioni dei metodi dell'interfaccia UserDetails
    Collection<? extends GrantedAuthority> getAuthorities();

    String getPassword();

    String getUsername();

    boolean isAccountNonExpired();

    boolean isAccountNonLocked();

    boolean isCredentialsNonExpired();

    boolean isEnabled();
}
