package com.transfert.domain.repository;

import com.transfert.domain.model.DossierTransfert;
import com.transfert.domain.model.StatutDossier;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DossierTransfertRepository {
    DossierTransfert save(DossierTransfert dossier);
    Optional<DossierTransfert> findById(UUID id);
    List<DossierTransfert> findByEtudiantEmail(String email);
    List<DossierTransfert> findByEtablissementSourceId(UUID etablissementId);
    boolean existsByEtudiantEmailAndStatutNot(String email, StatutDossier exclu);
    void deleteById(UUID id);
    
   
    void updateStatut(UUID id, StatutDossier statut);
}