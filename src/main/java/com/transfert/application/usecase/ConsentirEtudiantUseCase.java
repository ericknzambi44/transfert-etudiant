package com.transfert.application.usecase;

import com.transfert.domain.model.DossierTransfert;
import com.transfert.domain.model.HistoriqueAction;
import com.transfert.domain.model.StatutDossier;
import com.transfert.domain.repository.DossierTransfertRepository;
import com.transfert.domain.repository.HistoriqueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConsentirEtudiantUseCase {
    private final DossierTransfertRepository dossierRepository;
    private final HistoriqueRepository historiqueRepository;

    @Transactional
    public DossierTransfert execute(UUID dossierId) {
        // Vérifier que le dossier existe et est dans l'état attendu
        DossierTransfert dossier = dossierRepository.findById(dossierId)
                .orElseThrow(() -> new IllegalArgumentException("Dossier non trouvé"));
        if (dossier.getStatut() != StatutDossier.EN_ATTENTE_VALIDATION_SOURCE) {
            throw new IllegalStateException("Le consentement n'est possible qu'après validation source");
        }
        
        // Mise à jour directe via repository
        dossierRepository.updateStatut(dossierId, StatutDossier.EN_COURS_CIBLE);
        
        // Recharger le dossier
        DossierTransfert saved = dossierRepository.findById(dossierId)
                .orElseThrow(() -> new IllegalStateException("Impossible de recharger le dossier"));
        
        // Historique
        HistoriqueAction historique = HistoriqueAction.builder()
                .action("CONSENTEMENT")
                .description("L'étudiant a consenti au transfert")
                .utilisateurId("etudiant::" + dossier.getEtudiant().getId())
                .utilisateurType("ETUDIANT")
                .dossierId(dossierId)
                .dateAction(LocalDateTime.now())
                .build();
        historiqueRepository.save(historique);
        
        return saved;
    }
}