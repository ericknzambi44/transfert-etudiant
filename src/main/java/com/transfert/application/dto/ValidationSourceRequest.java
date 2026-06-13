package com.transfert.application.dto;

import jakarta.validation.constraints.NotBlank;
public class ValidationSourceRequest {
    private String commentaire;
    public String getCommentaire() { return commentaire; }
    public void setCommentaire(String commentaire) { this.commentaire = commentaire; }
}