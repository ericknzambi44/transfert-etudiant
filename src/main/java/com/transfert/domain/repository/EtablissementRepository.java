// domain/repository/EtablissementRepository.java
package com.transfert.domain.repository;

import com.transfert.domain.model.Etablissement;
import java.util.Optional;
import java.util.UUID;

public interface EtablissementRepository {
    Etablissement save(Etablissement etablissement);
    Optional<Etablissement> findById(UUID id);
    Optional<Etablissement> findByEmailContact(String email);
    boolean existsByNom(String nom);
}