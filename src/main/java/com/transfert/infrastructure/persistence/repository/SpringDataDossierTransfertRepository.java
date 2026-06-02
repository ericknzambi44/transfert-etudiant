package com.transfert.infrastructure.persistence.repository;

import com.transfert.infrastructure.persistence.entity.DossierTransfertEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface SpringDataDossierTransfertRepository extends JpaRepository<DossierTransfertEntity, UUID> {
    List<DossierTransfertEntity> findByEtudiantEmail(String email);
    List<DossierTransfertEntity> findByEtablissementSourceId(UUID etablissementId);
    boolean existsByEtudiantEmailAndStatutNot(String email, String statut);
}