// domain/model/StatutDossier.java
package com.transfert.domain.model;

public enum StatutDossier {
    BROUILLON,
    EN_ATTENTE_VALIDATION_SOURCE,
    EN_ATTENTE_CONSENTEMENT_ETUDIANT,
    EN_COURS_CIBLE,
    ACCEPTE,
    REFUSE,
    ANNULE;

    public boolean canTransitionTo(StatutDossier nouveau) {
        return switch (this) {
            case BROUILLON -> nouveau == EN_ATTENTE_VALIDATION_SOURCE || nouveau == ANNULE;
            case EN_ATTENTE_VALIDATION_SOURCE -> nouveau == EN_ATTENTE_CONSENTEMENT_ETUDIANT || nouveau == REFUSE;
            case EN_ATTENTE_CONSENTEMENT_ETUDIANT -> nouveau == EN_COURS_CIBLE || nouveau == ANNULE;
            case EN_COURS_CIBLE -> nouveau == ACCEPTE || nouveau == REFUSE;
            default -> false;
        };
    }
}