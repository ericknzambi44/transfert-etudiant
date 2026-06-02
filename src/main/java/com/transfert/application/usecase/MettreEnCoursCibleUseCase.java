// application/usecase/MettreEnCoursCibleUseCase.java (appelé automatiquement après consentement, mais exposé si besoin)
package com.transfert.application.usecase;

import com.transfert.domain.model.DossierTransfert;
import com.transfert.domain.repository.DossierTransfertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MettreEnCoursCibleUseCase {
    private final DossierTransfertRepository dossierRepository;

    @Transactional
    public DossierTransfert execute(UUID dossierId) {
        DossierTransfert dossier = dossierRepository.findById(dossierId)
                .orElseThrow(() -> new IllegalArgumentException("Dossier non trouvé"));
        dossier.mettreEnCoursCible();
        return dossierRepository.save(dossier);
    }
}