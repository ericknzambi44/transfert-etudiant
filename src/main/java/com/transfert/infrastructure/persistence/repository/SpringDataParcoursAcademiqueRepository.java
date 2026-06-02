package com.transfert.infrastructure.persistence.repository;

import com.transfert.infrastructure.persistence.entity.ParcoursAcademiqueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface SpringDataParcoursAcademiqueRepository extends JpaRepository<ParcoursAcademiqueEntity, UUID> {
    Optional<ParcoursAcademiqueEntity> findByDossierId(UUID dossierId);
}