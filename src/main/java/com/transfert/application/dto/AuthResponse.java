package com.transfert.application.dto;

import java.util.UUID;

public class AuthResponse {
    private String token;
    private String type;
    private String role;
    private String userType; // "ETABLISSEMENT" ou "ETUDIANT"
    private UUID etablissementId;

    // Constructeur avec 5 paramètres
    public AuthResponse(String token, String type, String role, String userType, UUID etablissementId) {
        this.token = token;
        this.type = type;
        this.role = role;
        this.userType = userType;
        this.etablissementId = etablissementId;
    }

    // Getters et setters
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getUserType() { return userType; }
    public void setUserType(String userType) { this.userType = userType; }
    public UUID getEtablissementId() { return etablissementId; }
    public void setEtablissementId(UUID etablissementId) { this.etablissementId = etablissementId; }
}