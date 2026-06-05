package com.transfert.infrastructure.web.controller;

import com.transfert.application.dto.ApiResponse;
import com.transfert.domain.model.Etudiant;
import com.transfert.domain.repository.EtudiantRepository;
import com.transfert.infrastructure.security.UserPrincipal;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/etudiants")
@RequiredArgsConstructor
public class EtudiantController {

    private final EtudiantRepository etudiantRepository;

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<Etudiant>> getCurrentStudent(@AuthenticationPrincipal UserPrincipal user) {
        if (user == null || !"ETUDIANT".equals(user.getUserType())) {
            return ResponseEntity.status(403).body(new ApiResponse<>(false, "Accès réservé aux étudiants", null));
        }
        Etudiant etudiant = etudiantRepository.findById(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("Étudiant non trouvé"));
        return ResponseEntity.ok(new ApiResponse<>(true, "Profil récupéré", etudiant));
    }

    @PutMapping("/me")
    public ResponseEntity<ApiResponse<Etudiant>> updateStudentProfile(@AuthenticationPrincipal UserPrincipal user,
                                                                      @Valid @RequestBody EtudiantUpdateRequest request) {
        if (user == null || !"ETUDIANT".equals(user.getUserType())) {
            return ResponseEntity.status(403).body(new ApiResponse<>(false, "Accès réservé aux étudiants", null));
        }
        Etudiant existing = etudiantRepository.findById(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("Étudiant non trouvé"));

        Etudiant updated = new Etudiant.Builder()
                .id(existing.getId())
                .nom(request.getNom() != null ? request.getNom() : existing.getNom())
                .prenom(request.getPrenom() != null ? request.getPrenom() : existing.getPrenom())
                .email(existing.getEmail())
                .dateNaissance(request.getDateNaissance() != null ? request.getDateNaissance() : existing.getDateNaissance())
                .identifiantNational(request.getIdentifiantNational() != null ? request.getIdentifiantNational() : existing.getIdentifiantNational())
                .build();

        Etudiant saved = etudiantRepository.save(updated);
        return ResponseEntity.ok(new ApiResponse<>(true, "Profil mis à jour", saved));
    }
}

// DTO pour la mise à jour (dans le même fichier ou séparé)
class EtudiantUpdateRequest {
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private String identifiantNational;
    // getters/setters manuels ou Lombok
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public LocalDate getDateNaissance() { return dateNaissance; }
    public void setDateNaissance(LocalDate dateNaissance) { this.dateNaissance = dateNaissance; }
    public String getIdentifiantNational() { return identifiantNational; }
    public void setIdentifiantNational(String identifiantNational) { this.identifiantNational = identifiantNational; }
}