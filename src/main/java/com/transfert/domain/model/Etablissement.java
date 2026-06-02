package com.transfert.domain.model;

import lombok.Getter;
import java.util.UUID;
import java.util.regex.Pattern;

@Getter
public class Etablissement {
    private final UUID id;
    private final String nom;
    private final String adresse;
    private final String emailContact;
    private final RoleEtablissement role;
    private final boolean actif;

    private Etablissement(Builder builder) {
        this.id = builder.id;
        this.nom = builder.nom;
        this.adresse = builder.adresse;
        this.emailContact = builder.emailContact;
        this.role = builder.role;
        this.actif = builder.actif;
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
            return new Etablissement(this);
        }
    }

    // Méthodes métier
    public boolean peutCreerDossier() {
        return actif && (role == RoleEtablissement.SOURCE || role == RoleEtablissement.BOTH);
    }

    public boolean peutAccepterEnCible() {
        return actif && (role == RoleEtablissement.CIBLE || role == RoleEtablissement.BOTH);
    }
}