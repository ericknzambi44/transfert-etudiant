package com.transfert.application.usecase;

import com.transfert.domain.model.DossierTransfert;
import com.transfert.domain.model.HistoriqueAction;
import com.transfert.domain.repository.DossierTransfertRepository;
import com.transfert.domain.repository.HistoriqueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccepterTransfertUseCase {
    private final DossierTransfertRepository dossierRepository;
    private final HistoriqueRepository historiqueRepository;

    @Transactional
    public DossierTransfert execute(UUID dossierId, UUID etablissementActeurId, String commentaire, boolean accepte) {
        DossierTransfert dossier = dossierRepository.findById(dossierId)
                .orElseThrow(() -> new IllegalArgumentException("Dossier non trouvé"));

        // Empêcher l'établissement source d'accepter son propre dossier
        if (dossier.getEtablissementSource().getId().equals(etablissementActeurId)) {
            throw new IllegalStateException("L'établissement source ne peut pas accepter ou refuser son propre dossier.");
        }

        if (accepte) {
            dossier.accepterParCible(commentaire);
        } else {
            dossier.refuserParCible(commentaire);
        }
        DossierTransfert saved = dossierRepository.save(dossier);

        HistoriqueAction historique = HistoriqueAction.builder()
                .action(accepte ? "ACCEPTATION_CIBLE" : "REFUS_CIBLE")
                .description(accepte ? "Transfert accepté par l'établissement cible" : "Transfert refusé par l'établissement cible")
                .utilisateurId(etablissementActeurId.toString())
                .utilisateurType("ETABLISSEMENT")
                .dossierId(dossierId)
                .dateAction(LocalDateTime.now())
                .build();
        historiqueRepository.save(historique);

        return saved;
    }
}



