// infrastructure/persistence/mapper/DossierTransfertMapper.java
package com.transfert.infrastructure.persistence.mapper;

import com.transfert.domain.model.DossierTransfert;
import com.transfert.infrastructure.persistence.entity.DossierTransfertEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {EtablissementMapper.class, EtudiantMapper.class})
public interface DossierTransfertMapper {
    @Mapping(target = "statut", expression = "java(com.transfert.infrastructure.persistence.entity.DossierTransfertEntity.StatutDossier.valueOf(domain.getStatut().name()))")
    DossierTransfertEntity toEntity(DossierTransfert domain);

    @Mapping(target = "statut", expression = "java(com.transfert.domain.model.StatutDossier.valueOf(entity.getStatut().name()))")
    DossierTransfert toDomain(DossierTransfertEntity entity);
}