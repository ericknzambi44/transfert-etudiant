package com.transfert.infrastructure.persistence.repository;

import com.transfert.infrastructure.persistence.entity.EtablissementEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SpringDataEtablissementRepository extends JpaRepository<EtablissementEntity, UUID> {
    Optional<EtablissementEntity> findByEmailContact(String email);
    boolean existsByNom(String nom);
}