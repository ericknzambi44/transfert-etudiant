// infrastructure/persistence/entity/EtablissementEntity.java
package com.transfert.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "etablissement")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EtablissementEntity {
    @Id
    private UUID id;
    @Column(nullable = false, unique = true)
    private String nom;
    private String adresse;
    @Column(nullable = false, unique = true)
    private String emailContact;
    private String password; // pour l'authentification
    @Enumerated(EnumType.STRING)
    private RoleEtablissement role;
    private boolean actif;

    public enum RoleEtablissement {
        SOURCE, CIBLE, BOTH
    }
}