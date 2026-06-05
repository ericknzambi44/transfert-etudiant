package com.transfert.infrastructure.persistence.entity;

import java.util.UUID;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "unite_enseignement")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UniteEnseignementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String code;
    private String nom;
    private int credits;
    private Double note;
}