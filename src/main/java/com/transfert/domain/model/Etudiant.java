package com.transfert.domain.model;

import lombok.Getter;
import java.time.LocalDate;
import java.util.UUID;
import java.util.regex.Pattern;

@Getter
public class Etudiant {
    private final UUID id;
    private final String nom;
    private final String prenom;
    private final String email;
    private final LocalDate dateNaissance;
    private final String identifiantNational;

    private Etudiant(Builder builder) {
        this.id = builder.id;
        this.nom = builder.nom;
        this.prenom = builder.prenom;
        this.email = builder.email;
        this.dateNaissance = builder.dateNaissance;
        this.identifiantNational = builder.identifiantNational;
    }

    public static class Builder {
        private UUID id = UUID.randomUUID();
        private String nom;
        private String prenom;
        private String email;
        private LocalDate dateNaissance;
        private String identifiantNational;

        public Builder id(UUID id) { this.id = id; return this; }
        public Builder nom(String nom) { this.nom = nom; return this; }
        public Builder prenom(String prenom) { this.prenom = prenom; return this; }
        public Builder email(String email) { this.email = email; return this; }
        public Builder dateNaissance(LocalDate dateNaissance) { this.dateNaissance = dateNaissance; return this; }
        public Builder identifiantNational(String identifiantNational) { this.identifiantNational = identifiantNational; return this; }

        public Etudiant build() {
            if (nom == null || nom.isBlank()) throw new IllegalArgumentException("Nom obligatoire");
            if (prenom == null || prenom.isBlank()) throw new IllegalArgumentException("Prénom obligatoire");
            if (email == null || !Pattern.matches("^[A-Za-z0-9+_.-]+@(.+)$", email))
                throw new IllegalArgumentException("Email étudiant invalide");
            if (dateNaissance == null || dateNaissance.isAfter(LocalDate.now()))
                throw new IllegalArgumentException("Date de naissance invalide");
            if (identifiantNational != null && !identifiantNational.matches("^[A-Z0-9]{8,12}$"))
                throw new IllegalArgumentException("Identifiant national doit être 8-12 caractères alphanumériques majuscules");
            return new Etudiant(this);
        }
    }
}