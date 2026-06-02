package com.transfert.infrastructure.persistence.repository;

import com.transfert.infrastructure.persistence.entity.EtudiantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface SpringDataEtudiantRepository extends JpaRepository<EtudiantEntity, UUID> {
    Optional<EtudiantEntity> findByEmail(String email);
    boolean existsByEmail(String email);
}