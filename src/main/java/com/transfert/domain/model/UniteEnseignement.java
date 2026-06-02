package com.transfert.domain.model;

public class UniteEnseignement {
    private String code;
    private String nom;
    private int credits;
    private Double note;

    public UniteEnseignement(String code, String nom, int credits, Double note) {
        if (code == null || code.isBlank()) throw new IllegalArgumentException("Code UE obligatoire");
        if (nom == null || nom.isBlank()) throw new IllegalArgumentException("Nom UE obligatoire");
        if (credits < 0 || credits > 30) throw new IllegalArgumentException("Crédits entre 0 et 30");
        if (note != null && (note < 0 || note > 20)) throw new IllegalArgumentException("Note entre 0 et 20");
        this.code = code;
        this.nom = nom;
        this.credits = credits;
        this.note = note;
    }

    public String getCode() { return code; }
    public String getNom() { return nom; }
    public int getCredits() { return credits; }
    public Double getNote() { return note; }
}