// domain/repository/EtudiantRepository.java
package com.transfert.domain.repository;

import com.transfert.domain.model.Etudiant;
import java.util.Optional;
import java.util.UUID;

public interface EtudiantRepository {
    Etudiant save(Etudiant etudiant);
    Optional<Etudiant> findById(UUID id);
    Optional<Etudiant> findByEmail(String email);
    boolean existsByEmail(String email);
}