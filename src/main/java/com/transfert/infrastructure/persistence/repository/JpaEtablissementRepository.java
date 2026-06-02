// infrastructure/persistence/repository/JpaEtablissementRepository.java
package com.transfert.infrastructure.persistence.repository;

import com.transfert.domain.model.Etablissement;
import com.transfert.domain.repository.EtablissementRepository;
import com.transfert.infrastructure.persistence.entity.EtablissementEntity;
import com.transfert.infrastructure.persistence.mapper.EtablissementMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class JpaEtablissementRepository implements EtablissementRepository {
    private final SpringDataEtablissementRepository jpaRepository;
    private final EtablissementMapper mapper;

    @Override
    public Etablissement save(Etablissement etablissement) {
        EtablissementEntity entity = mapper.toEntity(etablissement);
        EtablissementEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Etablissement> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<Etablissement> findByEmailContact(String email) {
        return jpaRepository.findByEmailContact(email).map(mapper::toDomain);
    }

    @Override
    public boolean existsByNom(String nom) {
        return jpaRepository.existsByNom(nom);
    }
}

