// application/usecase/GetDossierByIdUseCase.java
package com.transfert.application.usecase;

import com.transfert.domain.model.DossierTransfert;
import com.transfert.domain.repository.DossierTransfertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetDossierByIdUseCase {
    private final DossierTransfertRepository dossierRepository;

    public DossierTransfert execute(UUID id) {
        return dossierRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Dossier non trouvé"));
    }
}