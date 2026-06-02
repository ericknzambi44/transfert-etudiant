// infrastructure/persistence/repository/JpaParcoursAcademiqueRepository.java
package com.transfert.infrastructure.persistence.repository;

import com.transfert.domain.model.ParcoursAcademique;
import com.transfert.domain.repository.ParcoursAcademiqueRepository;
import com.transfert.infrastructure.persistence.entity.ParcoursAcademiqueEntity;
import com.transfert.infrastructure.persistence.mapper.ParcoursAcademiqueMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class JpaParcoursAcademiqueRepository implements ParcoursAcademiqueRepository {
    private final SpringDataParcoursAcademiqueRepository jpaRepository;
    private final ParcoursAcademiqueMapper mapper;

    @Override
    public ParcoursAcademique save(ParcoursAcademique parcours) {
        ParcoursAcademiqueEntity entity = mapper.toEntity(parcours);
        ParcoursAcademiqueEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<ParcoursAcademique> findByDossierId(UUID dossierId) {
        return jpaRepository.findByDossierId(dossierId).map(mapper::toDomain);
    }
}
