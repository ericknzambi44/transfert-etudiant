package com.transfert.infrastructure.web.controller;

import com.transfert.application.dto.ApiResponse;
import com.transfert.application.dto.AuthRequest;
import com.transfert.application.dto.AuthResponse;
import com.transfert.application.dto.EtudiantInscriptionRequest;
import com.transfert.infrastructure.persistence.entity.EtudiantEntity;
import com.transfert.infrastructure.persistence.repository.SpringDataEtudiantRepository;
import com.transfert.infrastructure.security.JwtTokenProvider;
import com.transfert.infrastructure.security.UserPrincipal;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/api/etudiants")
@RequiredArgsConstructor
@Slf4j
public class EtudiantAuthController {

    private final SpringDataEtudiantRepository etudiantRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UUID>> register(@Valid @RequestBody EtudiantInscriptionRequest request) {
        log.info("Tentative d'inscription étudiant: {}", request.getEmail());
        if (etudiantRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse<>(false, "Cet email est déjà utilisé", null));
        }
        EtudiantEntity etudiant = EtudiantEntity.builder()
                .id(UUID.randomUUID())
                .nom(request.getNom())
                .prenom(request.getPrenom())
                .email(request.getEmail())
                .dateNaissance(LocalDate.parse(request.getDateNaissance()))
                .identifiantNational(request.getIdentifiantNational())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        EtudiantEntity saved = etudiantRepository.save(etudiant);
        return ResponseEntity.ok(new ApiResponse<>(true, "Étudiant enregistré avec succès", saved.getId()));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@Valid @RequestBody AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        var userDetails = (UserPrincipal) authentication.getPrincipal();
        return ResponseEntity.ok(new ApiResponse<>(true, "Authentification réussie",
                new AuthResponse(jwt, "Bearer", userDetails.getRole(), userDetails.getUserType(), userDetails.getEtablissementId())));
    }
}