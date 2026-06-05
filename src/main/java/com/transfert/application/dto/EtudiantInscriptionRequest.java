package com.transfert.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class EtudiantInscriptionRequest {
    @NotBlank(message = "Le nom est requis")
    private String nom;

    @NotBlank(message = "Le prénom est requis")
    private String prenom;

    @NotBlank(message = "L'email est requis")
    @Email(message = "Email invalide")
    private String email;

    @NotBlank(message = "Le mot de passe est requis")
    private String password;

    @NotBlank(message = "La date de naissance est requise (format YYYY-MM-DD)")
    private String dateNaissance;

    private String identifiantNational;
}