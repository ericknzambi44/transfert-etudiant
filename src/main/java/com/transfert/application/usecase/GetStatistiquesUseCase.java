package com.transfert.application.usecase;

import com.transfert.application.dto.StatistiquesDTO;
import com.transfert.domain.model.StatutDossier;
import com.transfert.domain.repository.DossierTransfertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetStatistiquesUseCase {
    private final DossierTransfertRepository dossierRepository;

    public StatistiquesDTO execute(UUID etablissementId) {
        var dossiers = dossierRepository.findByEtablissementSourceId(etablissementId);
        long total = dossiers.size();
        long acceptes = dossiers.stream().filter(d -> d.getStatut() == StatutDossier.ACCEPTE).count();
        long refuses = dossiers.stream().filter(d -> d.getStatut() == StatutDossier.REFUSE).count();
        long enCours = dossiers.stream().filter(d -> d.getStatut() != StatutDossier.ACCEPTE && d.getStatut() != StatutDossier.REFUSE).count();
        long ceMois = dossiers.stream()
                .filter(d -> d.getDateCreation().getMonth() == LocalDateTime.now().getMonth())
                .count();
        return new StatistiquesDTO(total, acceptes, refuses, enCours, ceMois);
    }
}