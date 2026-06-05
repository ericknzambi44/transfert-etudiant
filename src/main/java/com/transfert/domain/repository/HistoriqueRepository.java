package com.transfert.domain.repository;

import com.transfert.domain.model.HistoriqueAction;
import java.util.List;
import java.util.UUID;

public interface HistoriqueRepository {
    HistoriqueAction save(HistoriqueAction historique);
    List<HistoriqueAction> findByDossierId(UUID dossierId);
    List<HistoriqueAction> findByUtilisateurId(String utilisateurId, String utilisateurType);
}