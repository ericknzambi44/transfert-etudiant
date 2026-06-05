package com.transfert.infrastructure.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class UserPrincipal implements UserDetails {
    private final UUID id;
    private final String email;
    private final String password;
    private final String role;   // "ETABLISSEMENT" ou "ETUDIANT"
    private final UUID etablissementId; // null pour étudiants
    private final String userType; // "ETABLISSEMENT" ou "ETUDIANT"

    public UserPrincipal(UUID id, String email, String password, String role, UUID etablissementId, String userType) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
        this.etablissementId = etablissementId;
        this.userType = userType;
    }

    public UserPrincipal(UUID id, String email, String password, String role, String userType) {
        this(id, email, password, role, null, userType);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role));
    }

    @Override public String getPassword() { return password; }
    @Override public String getUsername() { return email; }
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }

    public UUID getId() { return id; }
    public String getEmail() { return email; }
    public String getRole() { return role; }
    public UUID getEtablissementId() { return etablissementId; }
    public String getUserType() { return userType; }
}