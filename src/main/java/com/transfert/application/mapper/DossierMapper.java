package com.transfert.application.mapper;

import com.transfert.application.dto.CreationDossierRequest;
import com.transfert.application.dto.DossierResponse;
import com.transfert.domain.model.*;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DossierMapper {

    public Etudiant toEtudiant(CreationDossierRequest request) {
        return new Etudiant.Builder()
                .nom(request.getNomEtudiant() != null ? request.getNomEtudiant() : "À compléter")
                .prenom(request.getPrenomEtudiant() != null ? request.getPrenomEtudiant() : "À compléter")
                .email(request.getEmailEtudiant())
                .dateNaissance(request.getDateNaissance() != null ? LocalDate.parse(request.getDateNaissance()) : LocalDate.now().minusYears(18))
                .identifiantNational(request.getIdentifiantNational())
                .build();
    }

    public ParcoursAcademique toParcours(DossierTransfert dossier, CreationDossierRequest request) {
        String intitule = request.getIntituleDiplome() != null ? request.getIntituleDiplome() : "Non renseigné";
        int credits = request.getCreditsObtenus() != null ? request.getCreditsObtenus() : 0;
        List<UniteEnseignement> unites = request.getUnites() == null ? List.of() :
            request.getUnites().stream()
                .map(dto -> new UniteEnseignement(
                    dto.getCode() != null ? dto.getCode() : "",
                    dto.getNom() != null ? dto.getNom() : "",
                    dto.getCredits() != null ? dto.getCredits() : 0,
                    dto.getNote()
                ))
                .collect(Collectors.toList());

        return new ParcoursAcademique.Builder()
                .dossier(dossier)
                .intituleDiplome(intitule)
                .creditsObtenus(credits)
                .unitesValidees(unites)
                .build();
    }

    public DossierResponse toResponse(DossierTransfert dossier) {
        return new DossierResponse(
                dossier.getId(),
                dossier.getEtablissementSource().getNom(),
                dossier.getEtablissementSource().getId(),
                dossier.getEtudiant().getNom(),
                dossier.getEtudiant().getPrenom(),
                dossier.getEtudiant().getEmail(),
                dossier.getStatut().name(),
                dossier.getDateCreation(),
                dossier.getDateValidationSource(),
                dossier.getDateAcceptationCible(),
                dossier.getCommentaireSource(),
                dossier.getCommentaireCible()
        );
    }
}