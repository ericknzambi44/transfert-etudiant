package com.transfert.application.dto;

import jakarta.validation.constraints.*;
import java.util.List;

public class CreationDossierRequest {
    @NotBlank @Email private String emailEtudiant;
    @NotBlank private String nomEtudiant;
    @NotBlank private String prenomEtudiant;
    @NotNull private String dateNaissance;
    private String identifiantNational;
    @NotBlank private String intituleDiplome;
    @Min(0) @Max(300) private int creditsObtenus;
    private List<UniteDTO> unites;
    private String commentaireSource;

    // Getters et setters
    public String getEmailEtudiant() { return emailEtudiant; }
    public void setEmailEtudiant(String emailEtudiant) { this.emailEtudiant = emailEtudiant; }
    public String getNomEtudiant() { return nomEtudiant; }
    public void setNomEtudiant(String nomEtudiant) { this.nomEtudiant = nomEtudiant; }
    public String getPrenomEtudiant() { return prenomEtudiant; }
    public void setPrenomEtudiant(String prenomEtudiant) { this.prenomEtudiant = prenomEtudiant; }
    public String getDateNaissance() { return dateNaissance; }
    public void setDateNaissance(String dateNaissance) { this.dateNaissance = dateNaissance; }
    public String getIdentifiantNational() { return identifiantNational; }
    public void setIdentifiantNational(String identifiantNational) { this.identifiantNational = identifiantNational; }
    public String getIntituleDiplome() { return intituleDiplome; }
    public void setIntituleDiplome(String intituleDiplome) { this.intituleDiplome = intituleDiplome; }
    public int getCreditsObtenus() { return creditsObtenus; }
    public void setCreditsObtenus(int creditsObtenus) { this.creditsObtenus = creditsObtenus; }
    public List<UniteDTO> getUnites() { return unites; }
    public void setUnites(List<UniteDTO> unites) { this.unites = unites; }
    public String getCommentaireSource() { return commentaireSource; }
    public void setCommentaireSource(String commentaireSource) { this.commentaireSource = commentaireSource; }

    public static class UniteDTO {
        @NotBlank private String code;
        @NotBlank private String nom;
        @Min(0) @Max(30) private int credits;
        @Min(0) @Max(20) private Double note;

        public String getCode() { return code; }
        public void setCode(String code) { this.code = code; }
        public String getNom() { return nom; }
        public void setNom(String nom) { this.nom = nom; }
        public int getCredits() { return credits; }
        public void setCredits(int credits) { this.credits = credits; }
        public Double getNote() { return note; }
        public void setNote(Double note) { this.note = note; }
    }
}