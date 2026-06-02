// infrastructure/persistence/entity/EtudiantEntity.java
package com.transfert.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "etudiant")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EtudiantEntity {
    @Id
    private UUID id;
    @Column(nullable = false)
    private String nom;
    private String prenom;
    @Column(unique = true, nullable = false)
    private String email;
    private LocalDate dateNaissance;
    private String identifiantNational;
}