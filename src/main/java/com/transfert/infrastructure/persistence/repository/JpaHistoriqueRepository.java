package com.transfert.infrastructure.persistence.repository;

import com.transfert.domain.model.HistoriqueAction;
import com.transfert.domain.repository.HistoriqueRepository;
import com.transfert.infrastructure.persistence.entity.HistoriqueActionEntity;
import com.transfert.infrastructure.persistence.mapper.HistoriqueActionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class JpaHistoriqueRepository implements HistoriqueRepository {
    private final SpringDataHistoriqueActionRepository jpaRepository;
    private final HistoriqueActionMapper mapper;

    @Override
    public HistoriqueAction save(HistoriqueAction historique) {
        HistoriqueActionEntity entity = mapper.toEntity(historique);
        if (entity.getId() == null) {
            entity = jpaRepository.save(entity);
        } else {
            entity = jpaRepository.save(entity);
        }
        return mapper.toDomain(entity);
    }

    @Override
    public List<HistoriqueAction> findByDossierId(UUID dossierId) {
        return jpaRepository.findByDossierIdOrderByDateActionDesc(dossierId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<HistoriqueAction> findByUtilisateurId(String utilisateurId, String utilisateurType) {
        return jpaRepository.findByUtilisateurIdAndUtilisateurType(utilisateurId, utilisateurType).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
}