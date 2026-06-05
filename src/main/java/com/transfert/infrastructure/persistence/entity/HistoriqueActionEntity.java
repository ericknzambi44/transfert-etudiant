package com.transfert.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "historique_action")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoriqueActionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Column(nullable = false)
    private String action; // CREATION_DOSSIER, VALIDATION_SOURCE, CONSENTEMENT, ACCEPTATION_CIBLE, REFUS_CIBLE
    
    private String description;
    
    @Column(name = "utilisateur_id", nullable = false)
    private String utilisateurId; // UUID de l'utilisateur (établissement ou étudiant)
    
    @Column(name = "utilisateur_type", nullable = false)
    private String utilisateurType; // ETABLISSEMENT, ETUDIANT
    
    @Column(name = "dossier_id", nullable = false)
    private UUID dossierId;
    
    @Column(name = "date_action", nullable = false)
    private LocalDateTime dateAction;
}