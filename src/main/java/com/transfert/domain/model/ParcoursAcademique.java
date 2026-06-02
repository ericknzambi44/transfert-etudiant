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

    private ParcoursAcademique(Builder builder) {
        this.id = builder.id;
        this.dossier = builder.dossier;
        this.intituleDiplome = builder.intituleDiplome;
        this.creditsObtenus = builder.creditsObtenus;
        this.unitesValidees = builder.unitesValidees != null ? new ArrayList<>(builder.unitesValidees) : new ArrayList<>();
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
            return new ParcoursAcademique(this);
        }
    }
}