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
import java.time.LocalDateTime;
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
    private final HistoriqueRepository historiqueRepository;

    @Transactional
    public DossierTransfert execute(UUID etablissementSourceId, CreationDossierRequest request) {
        Etablissement source = etablissementRepository.findById(etablissementSourceId)
                .orElseThrow(() -> new IllegalArgumentException("Établissement source non trouvé"));
        // Plus de vérification sur le rôle (tous les établissements peuvent créer des dossiers)

        domainService.verifierUniciteTransfertActif(request.getEmailEtudiant());

        Etudiant etudiant = etudiantRepository.findByEmail(request.getEmailEtudiant())
                .orElseGet(() -> etudiantRepository.save(dossierMapper.toEtudiant(request)));

        DossierTransfert dossier = new DossierTransfert.Builder()
                .etablissementSource(source)
                .etudiant(etudiant)
                .commentaireSource(request.getCommentaireSource())
                .build();
        DossierTransfert savedDossier = dossierRepository.save(dossier);

        ParcoursAcademique parcours = dossierMapper.toParcours(savedDossier, request);
        parcoursRepository.save(parcours);

        HistoriqueAction historique = HistoriqueAction.builder()
                .action("CREATION_DOSSIER")
                .description("Dossier créé pour l'étudiant " + etudiant.getEmail())
                .utilisateurId(source.getId().toString())
                .utilisateurType("ETABLISSEMENT")
                .dossierId(savedDossier.getId())
                .dateAction(LocalDateTime.now())
                .build();
        historiqueRepository.save(historique);

        eventPublisher.publishEvent(new TransfertCreeEvent(savedDossier));
        return savedDossier;
    }
}