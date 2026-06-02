// application/usecase/AccepterTransfertUseCase.java
package com.transfert.application.usecase;

import com.transfert.domain.event.TransfertAccepteEvent;
import com.transfert.domain.model.DossierTransfert;
import com.transfert.domain.repository.DossierTransfertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccepterTransfertUseCase {
    private final DossierTransfertRepository dossierRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public DossierTransfert execute(UUID dossierId, String commentaire, boolean accepter) {
        DossierTransfert dossier = dossierRepository.findById(dossierId)
                .orElseThrow(() -> new IllegalArgumentException("Dossier non trouvé"));
        if (accepter) {
            dossier.accepterParCible(commentaire);
            eventPublisher.publishEvent(new TransfertAccepteEvent(dossier));
        } else {
            dossier.refuserParCible(commentaire);
        }
        return dossierRepository.save(dossier);
    }
}