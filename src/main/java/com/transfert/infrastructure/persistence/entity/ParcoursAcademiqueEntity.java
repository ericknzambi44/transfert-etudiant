// infrastructure/persistence/entity/ParcoursAcademiqueEntity.java
package com.transfert.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "parcours_academique")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParcoursAcademiqueEntity {
    @Id
    private UUID id;
    @ManyToOne
    private DossierTransfertEntity dossier;
    private String intituleDiplome;
    private int creditsObtenus;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<UniteEnseignementEntity> unitesValidees;
}