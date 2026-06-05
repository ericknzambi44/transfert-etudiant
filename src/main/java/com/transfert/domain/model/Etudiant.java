package com.transfert.domain.model;

import java.time.LocalDate;
import java.util.UUID;
import java.util.regex.Pattern;

public class Etudiant {
    private UUID id;
    private String nom;
    private String prenom;
    private String email;
    private LocalDate dateNaissance;
    private String identifiantNational;
    private String password;  // <-- NOUVEAU

    protected Etudiant() {}

    public Etudiant(UUID id, String nom, String prenom, String email, LocalDate dateNaissance, String identifiantNational, String password) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.dateNaissance = dateNaissance;
        this.identifiantNational = identifiantNational;
        this.password = password;
    }

    public static class Builder {
        private UUID id = UUID.randomUUID();
        private String nom;
        private String prenom;
        private String email;
        private LocalDate dateNaissance;
        private String identifiantNational;
        private String password;

        public Builder id(UUID id) { this.id = id; return this; }
        public Builder nom(String nom) { this.nom = nom; return this; }
        public Builder prenom(String prenom) { this.prenom = prenom; return this; }
        public Builder email(String email) { this.email = email; return this; }
        public Builder dateNaissance(LocalDate dateNaissance) { this.dateNaissance = dateNaissance; return this; }
        public Builder identifiantNational(String identifiantNational) { this.identifiantNational = identifiantNational; return this; }
        public Builder password(String password) { this.password = password; return this; }

        public Etudiant build() {
            if (nom == null || nom.isBlank()) throw new IllegalArgumentException("Nom obligatoire");
            if (prenom == null || prenom.isBlank()) throw new IllegalArgumentException("Prénom obligatoire");
            if (email == null || !Pattern.matches("^[A-Za-z0-9+_.-]+@(.+)$", email))
                throw new IllegalArgumentException("Email étudiant invalide");
            if (dateNaissance == null || dateNaissance.isAfter(LocalDate.now()))
                throw new IllegalArgumentException("Date de naissance invalide");
            if (identifiantNational != null && !identifiantNational.matches("^[A-Z0-9]{8,12}$"))
                throw new IllegalArgumentException("Identifiant national doit être 8-12 caractères alphanumériques majuscules");
            if (password == null || password.isBlank())
                throw new IllegalArgumentException("Mot de passe obligatoire");
            return new Etudiant(id, nom, prenom, email, dateNaissance, identifiantNational, password);
        }
    }

    // Getters
    public UUID getId() { return id; }
    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getEmail() { return email; }
    public LocalDate getDateNaissance() { return dateNaissance; }
    public String getIdentifiantNational() { return identifiantNational; }
    public String getPassword() { return password; }
}