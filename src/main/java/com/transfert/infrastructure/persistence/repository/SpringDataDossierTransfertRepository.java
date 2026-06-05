package com.transfert.infrastructure.persistence.repository;

import com.transfert.infrastructure.persistence.entity.DossierTransfertEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

public interface SpringDataDossierTransfertRepository extends JpaRepository<DossierTransfertEntity, UUID> {
    List<DossierTransfertEntity> findByEtudiantEmail(String email);
    List<DossierTransfertEntity> findByEtablissementSourceId(UUID etablissementId);
    boolean existsByEtudiantEmailAndStatutNot(String email, DossierTransfertEntity.StatutDossier statut);
    
    @Modifying
    @Transactional
    @Query("UPDATE DossierTransfertEntity d SET d.statut = :statut WHERE d.id = :id")
    int updateStatut(@Param("id") UUID id, @Param("statut") DossierTransfertEntity.StatutDossier statut);
}