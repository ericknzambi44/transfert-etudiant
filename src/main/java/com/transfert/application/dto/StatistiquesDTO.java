package com.transfert.application.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StatistiquesDTO {
    private long totalDossiers;
    private long dossiersAcceptes;
    private long dossiersRefuses;
    private long dossiersEnCours;
    private long dossiersCeMois;
}