package com.transfert.application.usecase;

import com.transfert.application.dto.CreationDossierRequest;
import com.transfert.application.mapper.DossierMapper;
import com.transfert.domain.event.TransfertCreeEvent;
import com.transfert.domain.model.*;
import com.transfert.domain.repository.*;
import com.transfert.domain.service.TransfertDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CreerDossierUseCase {
    private final EtablissementRepository etablissementRepository;
    private final EtudiantRepository etudiantRepository;
    private final DossierTransfertRepository dossierRepository;
    private final ParcoursAcademiqueRepository parcoursRepository;
    private final TransfertDomainService domainService;
    private final DossierMapper dossierMapper;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public DossierTransfert execute(UUID etablissementSourceId, CreationDossierRequest request) {
        Etablissement source = etablissementRepository.findById(etablissementSourceId)
                .orElseThrow(() -> new IllegalArgumentException("Établissement source non trouvé"));
        if (!source.peutCreerDossier())
            throw new IllegalStateException("Cet établissement n'est pas autorisé à créer des dossiers source");

        domainService.verifierUniciteTransfertActif(request.getEmailEtudiant());

        Etudiant etudiant = etudiantRepository.findByEmail(request.getEmailEtudiant())
                .orElseGet(() -> etudiantRepository.save(dossierMapper.toEtudiant(request)));

        // Correction : utiliser new DossierTransfert.Builder()
        DossierTransfert dossier = new DossierTransfert.Builder()
                .etablissementSource(source)
                .etudiant(etudiant)
                .commentaireSource(request.getCommentaireSource())
                .build();
        DossierTransfert savedDossier = dossierRepository.save(dossier);

        ParcoursAcademique parcours = dossierMapper.toParcours(savedDossier, request);
        parcoursRepository.save(parcours);

        eventPublisher.publishEvent(new TransfertCreeEvent(savedDossier));
        return savedDossier;
    }
}