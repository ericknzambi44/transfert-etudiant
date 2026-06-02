package com.transfert.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ParcoursAcademique {
    private UUID id;
    private DossierTransfert dossier;
    private String intituleDiplome;
    private int creditsObtenus;
    private List<UniteEnseignement> unitesValidees;

    protected ParcoursAcademique() {}

    public ParcoursAcademique(UUID id, DossierTransfert dossier, String intituleDiplome,
                              int creditsObtenus, List<UniteEnseignement> unitesValidees) {
        this.id = id;
        this.dossier = dossier;
        this.intituleDiplome = intituleDiplome;
        this.creditsObtenus = creditsObtenus;
        this.unitesValidees = unitesValidees != null ? new ArrayList<>(unitesValidees) : new ArrayList<>();
    }

    public static class Builder {
        private UUID id = UUID.randomUUID();
        private DossierTransfert dossier;
        private String intituleDiplome;
        private int creditsObtenus = 0;
        private List<UniteEnseignement> unitesValidees = new ArrayList<>();

        public Builder id(UUID id) { this.id = id; return this; }
        public Builder dossier(DossierTransfert dossier) { this.dossier = dossier; return this; }
        public Builder intituleDiplome(String intituleDiplome) { this.intituleDiplome = intituleDiplome; return this; }
        public Builder creditsObtenus(int creditsObtenus) { this.creditsObtenus = creditsObtenus; return this; }
        public Builder unitesValidees(List<UniteEnseignement> unitesValidees) { this.unitesValidees = unitesValidees; return this; }

        public ParcoursAcademique build() {
            if (dossier == null) throw new IllegalArgumentException("Dossier associé obligatoire");
            if (intituleDiplome == null || intituleDiplome.isBlank())
                throw new IllegalArgumentException("Intitulé du diplôme obligatoire");
            if (creditsObtenus < 0 || creditsObtenus > 300)
                throw new IllegalArgumentException("Crédits entre 0 et 300");
            int totalCredits = unitesValidees.stream().mapToInt(UniteEnseignement::getCredits).sum();
            if (totalCredits != creditsObtenus) {
                throw new IllegalArgumentException("La somme des crédits des UE ne correspond pas aux crédits obtenus déclarés");
            }
            return new ParcoursAcademique(id, dossier, intituleDiplome, creditsObtenus, unitesValidees);
        }
    }

    // Getters
    public UUID getId() { return id; }
    public DossierTransfert getDossier() { return dossier; }
    public String getIntituleDiplome() { return intituleDiplome; }
    public int getCreditsObtenus() { return creditsObtenus; }
    public List<UniteEnseignement> getUnitesValidees() { return unitesValidees; }
}