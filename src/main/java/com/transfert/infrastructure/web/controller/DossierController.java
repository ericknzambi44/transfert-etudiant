package com.transfert.infrastructure.web.controller;

import com.transfert.application.dto.*;
import com.transfert.application.mapper.DossierMapper;
import com.transfert.application.mapper.ParcoursMapper;
import com.transfert.application.usecase.*;
import com.transfert.domain.model.DossierTransfert;
import com.transfert.domain.model.HistoriqueAction;
import com.transfert.domain.model.ParcoursAcademique;
import com.transfert.domain.repository.HistoriqueRepository;
import com.transfert.infrastructure.security.UserPrincipal;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/dossiers")
@RequiredArgsConstructor
public class DossierController {
    private final CreerDossierUseCase creerDossierUseCase;
    private final ValiderDossierSourceUseCase validerSourceUseCase;
    private final ConsentirEtudiantUseCase consentirUseCase;
    private final AccepterTransfertUseCase accepterTransfertUseCase;
    private final RechercherDossierUseCase rechercherUseCase;
    private final ConsulterParcoursUseCase consulterParcoursUseCase;
    private final DossierMapper dossierMapper;
    private final ParcoursMapper parcoursMapper;
    private final ListerDossiersSourceUseCase listerDossiersSourceUseCase;
    private final GetDossierByIdUseCase getDossierByIdUseCase;
    private final HistoriqueRepository historiqueRepository;
    private final GetStatistiquesUseCase getStatistiquesUseCase;

    @PostMapping
    public ResponseEntity<ApiResponse<DossierResponse>> creerDossier(
            @AuthenticationPrincipal UserPrincipal user,
            @Valid @RequestBody CreationDossierRequest request) {
        DossierTransfert dossier = creerDossierUseCase.execute(user.getEtablissementId(), request);
        return ResponseEntity.ok(new ApiResponse<>(true, "Dossier créé", dossierMapper.toResponse(dossier)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<DossierResponse>>> listerDossiersSource(
            @AuthenticationPrincipal UserPrincipal user) {
        // Seuls les établissements (pas les étudiants) peuvent lister leurs dossiers
        if (user.getUserType().equals("ETUDIANT")) {
            return ResponseEntity.status(403).body(new ApiResponse<>(false, "Accès réservé aux établissements", null));
        }
        List<DossierTransfert> dossiers = listerDossiersSourceUseCase.execute(user.getEtablissementId());
        List<DossierResponse> responses = dossiers.stream().map(dossierMapper::toResponse).toList();
        return ResponseEntity.ok(new ApiResponse<>(true, "Liste des dossiers", responses));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<DossierResponse>> getDossierById(@PathVariable UUID id) {
        DossierTransfert dossier = getDossierByIdUseCase.execute(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Dossier trouvé", dossierMapper.toResponse(dossier)));
    }

    @PostMapping("/{id}/valider-source")
    public ResponseEntity<ApiResponse<DossierResponse>> validerSource(
            @PathVariable UUID id,
            @Valid @RequestBody ValidationSourceRequest request) {
        DossierTransfert dossier = validerSourceUseCase.execute(id, request.getCommentaire());
        return ResponseEntity.ok(new ApiResponse<>(true, "Dossier validé par la source", dossierMapper.toResponse(dossier)));
    }

    @PostMapping("/{id}/consentement")
    public ResponseEntity<ApiResponse<Void>> consentementEtudiant(
            @PathVariable UUID id,
            @Valid @RequestBody ConsentementEtudiantRequest request) {
        if (!request.isConsentement()) {
            throw new IllegalArgumentException("Le consentement est requis");
        }
        consentirUseCase.execute(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Consentement enregistré", null));
    }

    @PostMapping("/{id}/accepter-cible")
    public ResponseEntity<ApiResponse<DossierResponse>> accepterParCible(
            @AuthenticationPrincipal UserPrincipal user,
            @PathVariable UUID id,
            @Valid @RequestBody AcceptationCibleRequest request) {
        // Seuls les établissements peuvent accepter/refuser
        if (user.getUserType().equals("ETUDIANT")) {
            return ResponseEntity.status(403).body(new ApiResponse<>(false, "Accès réservé aux établissements", null));
        }
        DossierTransfert dossier = accepterTransfertUseCase.execute(id, request.getCommentaire(), request.isAccepte());
        return ResponseEntity.ok(new ApiResponse<>(true, request.isAccepte() ? "Transfert accepté" : "Transfert refusé", dossierMapper.toResponse(dossier)));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<DossierResponse>>> rechercherParEmail(@RequestParam String email) {
        List<DossierTransfert> dossiers = rechercherUseCase.execute(email);
        List<DossierResponse> responses = dossiers.stream().map(dossierMapper::toResponse).toList();
        return ResponseEntity.ok(new ApiResponse<>(true, "Résultats trouvés", responses));
    }

    @GetMapping("/{id}/parcours")
    public ResponseEntity<ApiResponse<ParcoursResponse>> getParcours(@PathVariable UUID id) {
        ParcoursAcademique parcours = consulterParcoursUseCase.execute(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Parcours trouvé", parcoursMapper.toResponse(parcours)));
    }

    @GetMapping("/{id}/historique")
    public ResponseEntity<ApiResponse<List<HistoriqueAction>>> getHistorique(@PathVariable UUID id) {
        List<HistoriqueAction> historique = historiqueRepository.findByDossierId(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Historique récupéré", historique));
    }

    @GetMapping("/statistiques")
    public ResponseEntity<ApiResponse<StatistiquesDTO>> getStatistiques(@AuthenticationPrincipal UserPrincipal user) {
        if (user.getUserType().equals("ETUDIANT")) {
            StatistiquesDTO empty = new StatistiquesDTO(0, 0, 0, 0, 0);
            return ResponseEntity.ok(new ApiResponse<>(true, "Stats étudiant (à compléter)", empty));
        } else {
            StatistiquesDTO stats = getStatistiquesUseCase.execute(user.getEtablissementId());
            return ResponseEntity.ok(new ApiResponse<>(true, "Statistiques récupérées", stats));
        }
    }
}