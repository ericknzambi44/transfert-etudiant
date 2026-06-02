// infrastructure/persistence/repository/JpaDossierTransfertRepository.java
package com.transfert.infrastructure.persistence.repository;

import com.transfert.domain.model.DossierTransfert;
import com.transfert.domain.model.StatutDossier;
import com.transfert.domain.repository.DossierTransfertRepository;
import com.transfert.infrastructure.persistence.entity.DossierTransfertEntity;
import com.transfert.infrastructure.persistence.mapper.DossierTransfertMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class JpaDossierTransfertRepository implements DossierTransfertRepository {
    private final SpringDataDossierTransfertRepository jpaRepository;
    private final DossierTransfertMapper mapper;

    @Override
    public DossierTransfert save(DossierTransfert dossier) {
        DossierTransfertEntity entity = mapper.toEntity(dossier);
        DossierTransfertEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<DossierTransfert> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<DossierTransfert> findByEtudiantEmail(String email) {
        return jpaRepository.findByEtudiantEmail(email).stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<DossierTransfert> findByEtablissementSourceId(UUID etablissementId) {
        return jpaRepository.findByEtablissementSourceId(etablissementId).stream().map(mapper::toDomain).toList();
    }

    @Override
    public boolean existsByEtudiantEmailAndStatutNot(String email, StatutDossier exclu) {
        return jpaRepository.existsByEtudiantEmailAndStatutNot(email, exclu.name());
    }

    @Override
    public void deleteById(UUID id) {
        jpaRepository.deleteById(id);
    }
}

