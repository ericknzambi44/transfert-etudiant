// domain/repository/ParcoursAcademiqueRepository.java
package com.transfert.domain.repository;

import com.transfert.domain.model.ParcoursAcademique;
import java.util.Optional;
import java.util.UUID;

public interface ParcoursAcademiqueRepository {
    ParcoursAcademique save(ParcoursAcademique parcours);
    Optional<ParcoursAcademique> findByDossierId(UUID dossierId);
}