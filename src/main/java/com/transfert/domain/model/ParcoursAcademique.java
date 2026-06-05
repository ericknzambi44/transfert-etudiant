package com.transfert.domain.model;

import lombok.Getter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class ParcoursAcademique {
    private final UUID id;
    private final DossierTransfert dossier;
    private final String intituleDiplome;
    private final int creditsObtenus;
    private final List<UniteEnseignement> unitesValidees;

    protected ParcoursAcademique() {
        this.id = null;
        this.dossier = null;
        this.intituleDiplome = null;
        this.creditsObtenus = 0;
        this.unitesValidees = new ArrayList<>();
    }

    public ParcoursAcademique(UUID id, DossierTransfert dossier, String intituleDiplome,
                              int creditsObtenus, List<UniteEnseignement> unitesValidees) {
        if (dossier == null) throw new IllegalArgumentException("Le dossier ne peut être null");
        if (intituleDiplome == null || intituleDiplome.isBlank()) {
            this.intituleDiplome = "Non renseigné";
        } else {
            this.intituleDiplome = intituleDiplome;
        }
        if (creditsObtenus < 0 || creditsObtenus > 300)
            throw new IllegalArgumentException("Les crédits doivent être entre 0 et 300");
        int totalCredits = unitesValidees.stream().mapToInt(UniteEnseignement::getCredits).sum();
        if (totalCredits != creditsObtenus) {
            throw new IllegalArgumentException("La somme des crédits des UE ne correspond pas aux crédits obtenus");
        }
        this.id = id;
        this.dossier = dossier;
        this.creditsObtenus = creditsObtenus;
        this.unitesValidees = new ArrayList<>(unitesValidees);
    }

    public static class Builder {
        private UUID id = UUID.randomUUID();
        private DossierTransfert dossier;
        private String intituleDiplome = "Non renseigné";
        private int creditsObtenus = 0;
        private List<UniteEnseignement> unitesValidees = new ArrayList<>();

        public Builder id(UUID id) { this.id = id; return this; }
        public Builder dossier(DossierTransfert dossier) { this.dossier = dossier; return this; }
        public Builder intituleDiplome(String intituleDiplome) { this.intituleDiplome = intituleDiplome; return this; }
        public Builder creditsObtenus(int creditsObtenus) { this.creditsObtenus = creditsObtenus; return this; }
        public Builder unitesValidees(List<UniteEnseignement> unitesValidees) { this.unitesValidees = unitesValidees; return this; }

        public ParcoursAcademique build() {
            return new ParcoursAcademique(id, dossier, intituleDiplome, creditsObtenus, unitesValidees);
        }
    }
}