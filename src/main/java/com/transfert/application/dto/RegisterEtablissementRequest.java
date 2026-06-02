package com.transfert.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
public class RegisterEtablissementRequest {
    @NotBlank private String nom;
    private String adresse;
    @NotBlank @Email private String emailContact;
    @NotBlank private String password;
    private String role; // SOURCE, CIBLE, BOTH

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getAdresse() { return adresse; }
    public void setAdresse(String adresse) { this.adresse = adresse; }
    public String getEmailContact() { return emailContact; }
    public void setEmailContact(String emailContact) { this.emailContact = emailContact; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}