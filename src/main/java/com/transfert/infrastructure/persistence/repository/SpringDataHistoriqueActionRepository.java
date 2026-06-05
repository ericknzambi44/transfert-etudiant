package com.transfert.infrastructure.persistence.repository;

import com.transfert.infrastructure.persistence.entity.HistoriqueActionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface SpringDataHistoriqueActionRepository extends JpaRepository<HistoriqueActionEntity, UUID> {
    List<HistoriqueActionEntity> findByDossierIdOrderByDateActionDesc(UUID dossierId);
    List<HistoriqueActionEntity> findByUtilisateurIdAndUtilisateurType(String utilisateurId, String utilisateurType);
}