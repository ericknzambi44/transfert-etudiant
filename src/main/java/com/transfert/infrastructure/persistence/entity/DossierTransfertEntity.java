// infrastructure/persistence/entity/DossierTransfertEntity.java
package com.transfert.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "dossier_transfert")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DossierTransfertEntity {
    @Id
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "etablissement_source_id")
    private EtablissementEntity etablissementSource;
    @ManyToOne
    private EtudiantEntity etudiant;
    @Enumerated(EnumType.STRING)
    private StatutDossier statut;
    private LocalDateTime dateCreation;
    private LocalDateTime dateValidationSource;
    private LocalDateTime dateAcceptationCible;
    private String commentaireSource;
    private String commentaireCible;

    public enum StatutDossier {
        BROUILLON, EN_ATTENTE_VALIDATION_SOURCE, EN_ATTENTE_CONSENTEMENT_ETUDIANT, EN_COURS_CIBLE, ACCEPTE, REFUSE, ANNULE
    }
}