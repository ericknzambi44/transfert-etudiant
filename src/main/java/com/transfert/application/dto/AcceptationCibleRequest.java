package com.transfert.application.dto;

public class AcceptationCibleRequest {
    private String commentaire;
    private boolean accepte;
    public String getCommentaire() { return commentaire; }
    public void setCommentaire(String commentaire) { this.commentaire = commentaire; }
    public boolean isAccepte() { return accepte; }
    public void setAccepte(boolean accepte) { this.accepte = accepte; }
}