package com.transfert.domain.model;

import lombok.Getter;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class DossierTransfert {
    private final UUID id;
    private final Etablissement etablissementSource;
    private final Etudiant etudiant;
    private StatutDossier statut;
    private final LocalDateTime dateCreation;
    private LocalDateTime dateValidationSource;
    private LocalDateTime dateAcceptationCible;
    private String commentaireSource;
    private String commentaireCible;

    private DossierTransfert(Builder builder) {
        this.id = builder.id;
        this.etablissementSource = builder.etablissementSource;
        this.etudiant = builder.etudiant;
        this.statut = builder.statut;
        this.dateCreation = builder.dateCreation;
        this.dateValidationSource = builder.dateValidationSource;
        this.dateAcceptationCible = builder.dateAcceptationCible;
        this.commentaireSource = builder.commentaireSource;
        this.commentaireCible = builder.commentaireCible;
    }

    public static class Builder {
        private UUID id = UUID.randomUUID();
        private Etablissement etablissementSource;
        private Etudiant etudiant;
        private StatutDossier statut = StatutDossier.BROUILLON;
        private LocalDateTime dateCreation = LocalDateTime.now();
        private LocalDateTime dateValidationSource;
        private LocalDateTime dateAcceptationCible;
        private String commentaireSource;
        private String commentaireCible;

        public Builder id(UUID id) { this.id = id; return this; }
        public Builder etablissementSource(Etablissement etablissementSource) { this.etablissementSource = etablissementSource; return this; }
        public Builder etudiant(Etudiant etudiant) { this.etudiant = etudiant; return this; }
        public Builder statut(StatutDossier statut) { this.statut = statut; return this; }
        public Builder dateCreation(LocalDateTime dateCreation) { this.dateCreation = dateCreation; return this; }
        public Builder commentaireSource(String commentaireSource) { this.commentaireSource = commentaireSource; return this; }

        public DossierTransfert build() {
            if (etablissementSource == null) throw new IllegalArgumentException("Établissement source obligatoire");
            if (!etablissementSource.peutCreerDossier())
                throw new IllegalArgumentException("Cet établissement n'a pas le droit de créer un dossier source");
            if (etudiant == null) throw new IllegalArgumentException("Étudiant obligatoire");
            return new DossierTransfert(this);
        }
    }

    // Méthodes métier (modifient l'état)
    public void validerParSource(String commentaire) {
        if (this.statut != StatutDossier.BROUILLON)
            throw new IllegalStateException("Seul un dossier en BROUILLON peut être validé par la source");
        this.statut = StatutDossier.EN_ATTENTE_VALIDATION_SOURCE;
        this.dateValidationSource = LocalDateTime.now();
        this.commentaireSource = commentaire;
    }

    public void consentirEtudiant() {
        if (this.statut != StatutDossier.EN_ATTENTE_VALIDATION_SOURCE)
            throw new IllegalStateException("Le consentement étudiant n'est possible qu'après validation source");
        this.statut = StatutDossier.EN_ATTENTE_CONSENTEMENT_ETUDIANT;
    }

    public void mettreEnCoursCible() {
        if (this.statut != StatutDossier.EN_ATTENTE_CONSENTEMENT_ETUDIANT)
            throw new IllegalStateException("Impossible de passer en cours cible sans consentement");
        this.statut = StatutDossier.EN_COURS_CIBLE;
    }

    public void accepterParCible(String commentaire) {
        if (this.statut != StatutDossier.EN_COURS_CIBLE)
            throw new IllegalStateException("Seul un dossier en cours cible peut être accepté");
        this.statut = StatutDossier.ACCEPTE;
        this.dateAcceptationCible = LocalDateTime.now();
        this.commentaireCible = commentaire;
    }

    public void refuserParCible(String commentaire) {
        if (this.statut != StatutDossier.EN_COURS_CIBLE)
            throw new IllegalStateException("Seul un dossier en cours cible peut être refusé");
        this.statut = StatutDossier.REFUSE;
        this.commentaireCible = commentaire;
    }

    public void annuler() {
        if (this.statut == StatutDossier.ACCEPTE || this.statut == StatutDossier.REFUSE)
            throw new IllegalStateException("Un dossier accepté ou refusé ne peut être annulé");
        this.statut = StatutDossier.ANNULE;
    }
}