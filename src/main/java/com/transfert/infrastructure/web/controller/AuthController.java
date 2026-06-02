// infrastructure/web/controller/AuthController.java
package com.transfert.infrastructure.web.controller;

import com.transfert.application.dto.ApiResponse;
import com.transfert.application.dto.AuthRequest;
import com.transfert.application.dto.AuthResponse;
import com.transfert.application.dto.RegisterEtablissementRequest;
import com.transfert.infrastructure.persistence.entity.EtablissementEntity;
import com.transfert.infrastructure.persistence.repository.SpringDataEtablissementRepository;
import com.transfert.infrastructure.security.JwtTokenProvider;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final SpringDataEtablissementRepository etablissementRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ApiResponse<AuthResponse> authenticateUser(@Valid @RequestBody AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        var userDetails = (com.transfert.infrastructure.security.UserPrincipal) authentication.getPrincipal();
        return new ApiResponse<>(true, "Authentification réussie",
                new AuthResponse(jwt, "Bearer", userDetails.getRole(), userDetails.getEtablissementId()));
    }
}