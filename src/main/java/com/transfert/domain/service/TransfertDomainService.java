// domain/service/TransfertDomainService.java
package com.transfert.domain.service;

import com.transfert.domain.repository.DossierTransfertRepository;
import com.transfert.domain.model.StatutDossier;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransfertDomainService {
    private final DossierTransfertRepository dossierRepository;

    public void verifierUniciteTransfertActif(String emailEtudiant) {
        if (dossierRepository.existsByEtudiantEmailAndStatutNot(emailEtudiant, StatutDossier.ACCEPTE)) {
            throw new IllegalStateException("Un dossier de transfert actif existe déjà pour cet étudiant");
        }
    }
}