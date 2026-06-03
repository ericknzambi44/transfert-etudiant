package com.transfert.infrastructure.persistence.repository;

import com.transfert.domain.model.Etudiant;
import com.transfert.domain.repository.EtudiantRepository;
import com.transfert.infrastructure.persistence.entity.EtudiantEntity;
import com.transfert.infrastructure.persistence.mapper.EtudiantMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class JpaEtudiantRepository implements EtudiantRepository {
    private final SpringDataEtudiantRepository jpaRepository;
    private final EtudiantMapper mapper;

    @Override
    public Etudiant save(Etudiant etudiant) {
        EtudiantEntity entity = mapper.toEntity(etudiant);
        EtudiantEntity saved = jpaRepository.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Etudiant> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<Etudiant> findByEmail(String email) {
        return jpaRepository.findByEmail(email).map(mapper::toDomain);
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaRepository.existsByEmail(email);
    }
}