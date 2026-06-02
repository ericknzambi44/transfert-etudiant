// domain/model/UniteEnseignement.java
package com.transfert.domain.model;

import lombok.Getter;
import lombok.Builder;

@Getter
@Builder
public class UniteEnseignement {
    private String code;
    private String nom;
    private int credits;
    private Double note;

    public static class UniteEnseignementBuilder {
        public UniteEnseignement build() {
            if (code == null || code.isBlank()) throw new IllegalArgumentException("Code UE obligatoire");
            if (nom == null || nom.isBlank()) throw new IllegalArgumentException("Nom UE obligatoire");
            if (credits < 0 || credits > 30) throw new IllegalArgumentException("Crédits entre 0 et 30");
            if (note != null && (note < 0 || note > 20)) throw new IllegalArgumentException("Note entre 0 et 20");
            return new UniteEnseignement(code, nom, credits, note);
        }
    }
}