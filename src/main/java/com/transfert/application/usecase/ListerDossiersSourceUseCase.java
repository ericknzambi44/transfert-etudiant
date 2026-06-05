// application/usecase/ListerDossiersSourceUseCase.java
package com.transfert.application.usecase;

import com.transfert.domain.model.DossierTransfert;
import com.transfert.domain.repository.DossierTransfertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ListerDossiersSourceUseCase {
    private final DossierTransfertRepository dossierRepository;

    public List<DossierTransfert> execute(UUID etablissementSourceId) {
        return dossierRepository.findByEtablissementSourceId(etablissementSourceId);
    }
}