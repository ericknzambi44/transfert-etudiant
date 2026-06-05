package com.transfert.domain.model;

import java.util.UUID;
import java.util.regex.Pattern;

public class Etablissement {
    private UUID id;
    private String nom;
    private String adresse;
    private String emailContact;
    private boolean actif;

    protected Etablissement() {}

    public Etablissement(UUID id, String nom, String adresse, String emailContact, boolean actif) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.emailContact = emailContact;
        this.actif = actif;
    }

    public static class Builder {
        private UUID id = UUID.randomUUID();
        private String nom;
        private String adresse;
        private String emailContact;
        private boolean actif = true;

        public Builder id(UUID id) { this.id = id; return this; }
        public Builder nom(String nom) { this.nom = nom; return this; }
        public Builder adresse(String adresse) { this.adresse = adresse; return this; }
        public Builder emailContact(String emailContact) { this.emailContact = emailContact; return this; }
        public Builder actif(boolean actif) { this.actif = actif; return this; }

        public Etablissement build() {
            if (nom == null || nom.isBlank()) throw new IllegalArgumentException("Le nom est obligatoire");
            if (emailContact == null || !Pattern.matches("^[A-Za-z0-9+_.-]+@(.+)$", emailContact))
                throw new IllegalArgumentException("Email invalide");
            return new Etablissement(id, nom, adresse, emailContact, actif);
        }
    }

    public boolean peutCreerDossier() {
        return actif;
    }

    public boolean peutAccepterEnCible() {
        return actif;
    }

    // Getters
    public UUID getId() { return id; }
    public String getNom() { return nom; }
    public String getAdresse() { return adresse; }
    public String getEmailContact() { return emailContact; }
    public boolean isActif() { return actif; }
}