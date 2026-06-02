package com.transfert.application.dto;

import java.util.UUID;

public class AuthResponse {
    private String token;
    private String type;
    private String role;
    private UUID etablissementId;

    // Constructeur explicite avec les 4 paramètres
    public AuthResponse(String token, String type, String role, UUID etablissementId) {
        this.token = token;
        this.type = type;
        this.role = role;
        this.etablissementId = etablissementId;
    }

    // Getters et setters
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public UUID getEtablissementId() { return etablissementId; }
    public void setEtablissementId(UUID etablissementId) { this.etablissementId = etablissementId; }
}