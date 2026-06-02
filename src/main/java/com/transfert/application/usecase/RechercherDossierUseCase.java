// application/usecase/RechercherDossierUseCase.java
package com.transfert.application.usecase;

import com.transfert.domain.model.DossierTransfert;
import com.transfert.domain.repository.DossierTransfertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RechercherDossierUseCase {
    private final DossierTransfertRepository dossierRepository;

    public List<DossierTransfert> execute(String emailEtudiant) {
        return dossierRepository.findByEtudiantEmail(emailEtudiant);
    }
}