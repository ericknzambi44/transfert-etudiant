package com.transfert.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import java.util.List;

@Data
public class CreationDossierRequest {
    @NotBlank(message = "L'email de l'étudiant est obligatoire")
    @Email(message = "Format d'email invalide")
    private String emailEtudiant;
    
    // Tous les autres champs sont optionnels pour simplifier la création
    private String nomEtudiant;
    private String prenomEtudiant;
    private String dateNaissance; // format YYYY-MM-DD
    private String identifiantNational;
    private String intituleDiplome;
    private Integer creditsObtenus;
    private List<UniteDTO> unites;
    private String commentaireSource;

    @Data
    public static class UniteDTO {
        private String code;
        private String nom;
        private Integer credits;
        private Double note;
    }
}