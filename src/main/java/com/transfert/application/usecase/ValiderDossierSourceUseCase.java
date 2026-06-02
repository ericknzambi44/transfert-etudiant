// application/usecase/ValiderDossierSourceUseCase.java
package com.transfert.application.usecase;

import com.transfert.domain.model.DossierTransfert;
import com.transfert.domain.repository.DossierTransfertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ValiderDossierSourceUseCase {
    private final DossierTransfertRepository dossierRepository;

    @Transactional
    public DossierTransfert execute(UUID dossierId, String commentaire) {
        DossierTransfert dossier = dossierRepository.findById(dossierId)
                .orElseThrow(() -> new IllegalArgumentException("Dossier non trouvé"));
        dossier.validerParSource(commentaire);
        return dossierRepository.save(dossier);
    }
}