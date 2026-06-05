package com.transfert.infrastructure.persistence.mapper;

import com.transfert.domain.model.HistoriqueAction;
import com.transfert.infrastructure.persistence.entity.HistoriqueActionEntity;
import org.springframework.stereotype.Component;

@Component
public class HistoriqueActionMapper {
    public HistoriqueActionEntity toEntity(HistoriqueAction domain) {
        if (domain == null) return null;
        return HistoriqueActionEntity.builder()
                .id(domain.getId())
                .action(domain.getAction())
                .description(domain.getDescription())
                .utilisateurId(domain.getUtilisateurId())
                .utilisateurType(domain.getUtilisateurType())
                .dossierId(domain.getDossierId())
                .dateAction(domain.getDateAction())
                .build();
    }

    public HistoriqueAction toDomain(HistoriqueActionEntity entity) {
        if (entity == null) return null;
        return HistoriqueAction.builder()
                .id(entity.getId())
                .action(entity.getAction())
                .description(entity.getDescription())
                .utilisateurId(entity.getUtilisateurId())
                .utilisateurType(entity.getUtilisateurType())
                .dossierId(entity.getDossierId())
                .dateAction(entity.getDateAction())
                .build();
    }
}