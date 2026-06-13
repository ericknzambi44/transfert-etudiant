package com.transfert.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
public class DossierResponse {
    private UUID id;
    private String etablissementSourceNom;
    private UUID etablissementSourceId;
    private String etudiantNom;
    private String etudiantPrenom;
    private String etudiantEmail;
    private String statut;
    private LocalDateTime dateCreation;
    private LocalDateTime dateValidationSource;
    private LocalDateTime dateAcceptationCible;
    private String commentaireSource;
    private String commentaireCible;
}