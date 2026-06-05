// infrastructure/web/controller/EtablissementController.java
package com.transfert.infrastructure.web.controller;

import com.transfert.application.dto.ApiResponse;
import com.transfert.application.dto.RegisterEtablissementRequest;
import com.transfert.infrastructure.persistence.entity.EtablissementEntity;
import com.transfert.infrastructure.persistence.repository.SpringDataEtablissementRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/etablissements")
@RequiredArgsConstructor
public class EtablissementController {
    private final SpringDataEtablissementRepository repository;
    private final PasswordEncoder passwordEncoder;

  @PostMapping("/register")
public ResponseEntity<ApiResponse<UUID>> register(@Valid @RequestBody RegisterEtablissementRequest request) {
    if (repository.existsByNom(request.getNom())) {
        throw new IllegalArgumentException("Un établissement avec ce nom existe déjà");
    }
    EtablissementEntity entity = EtablissementEntity.builder()
            .id(UUID.randomUUID())
            .nom(request.getNom())
            .adresse(request.getAdresse())
            .emailContact(request.getEmailContact())
            .password(passwordEncoder.encode(request.getPassword()))
            .actif(true)
            .build();
    EtablissementEntity saved = repository.save(entity);
    return ResponseEntity.ok(new ApiResponse<>(true, "Établissement enregistré avec succès", saved.getId()));
}
}