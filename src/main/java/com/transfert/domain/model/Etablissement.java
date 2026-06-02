package com.transfert.domain.model;

import java.util.UUID;
import java.util.regex.Pattern;

public class Etablissement {
    private UUID id;
    private String nom;
    private String adresse;
    private String emailContact;
    private RoleEtablissement role;
    private boolean actif;

    protected Etablissement() {}

    public Etablissement(UUID id, String nom, String adresse, String emailContact, RoleEtablissement role, boolean actif) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.emailContact = emailContact;
        this.role = role;
        this.actif = actif;
    }

    public static class Builder {
        private UUID id = UUID.randomUUID();
        private String nom;
        private String adresse;
        private String emailContact;
        private RoleEtablissement role = RoleEtablissement.CIBLE;
        private boolean actif = true;

        public Builder id(UUID id) { this.id = id; return this; }
        public Builder nom(String nom) { this.nom = nom; return this; }
        public Builder adresse(String adresse) { this.adresse = adresse; return this; }
        public Builder emailContact(String emailContact) { this.emailContact = emailContact; return this; }
        public Builder role(RoleEtablissement role) { this.role = role; return this; }
        public Builder actif(boolean actif) { this.actif = actif; return this; }

        public Etablissement build() {
            if (nom == null || nom.isBlank()) throw new IllegalArgumentException("Le nom est obligatoire");
            if (emailContact == null || !Pattern.matches("^[A-Za-z0-9+_.-]+@(.+)$", emailContact))
                throw new IllegalArgumentException("Email invalide");
            return new Etablissement(id, nom, adresse, emailContact, role, actif);
        }
    }

    public boolean peutCreerDossier() {
        return actif && (role == RoleEtablissement.SOURCE || role == RoleEtablissement.BOTH);
    }

    public boolean peutAccepterEnCible() {
        return actif && (role == RoleEtablissement.CIBLE || role == RoleEtablissement.BOTH);
    }

    // Getters
    public UUID getId() { return id; }
    public String getNom() { return nom; }
    public String getAdresse() { return adresse; }
    public String getEmailContact() { return emailContact; }
    public RoleEtablissement getRole() { return role; }
    public boolean isActif() { return actif; }
}