package com.transfert.domain.model;

import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
public class HistoriqueAction {
    private UUID id;
    private String action;
    private String description;
    private String utilisateurId;
    private String utilisateurType;
    private UUID dossierId;
    private LocalDateTime dateAction;
}