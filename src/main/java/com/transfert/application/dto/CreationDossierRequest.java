// application/dto/CreationDossierRequest.java
package com.transfert.application.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.util.List;

@Data
public class CreationDossierRequest {
    @NotBlank @Email
    private String emailEtudiant;

    @NotBlank
    private String nomEtudiant;

    @NotBlank
    private String prenomEtudiant;

    @NotNull
    private String dateNaissance; // format yyyy-MM-dd

    private String identifiantNational;

    @NotBlank
    private String intituleDiplome;

    @Min(0) @Max(300)
    private int creditsObtenus;

    private List<UniteDTO> unites;

    private String commentaireSource;

    @Data
    public static class UniteDTO {
        @NotBlank private String code;
        @NotBlank private String nom;
        @Min(0) @Max(30) private int credits;
        @Min(0) @Max(20) private Double note;
    }
}