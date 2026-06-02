// infrastructure/persistence/mapper/ParcoursAcademiqueMapper.java
package com.transfert.infrastructure.persistence.mapper;

import com.transfert.domain.model.ParcoursAcademique;
import com.transfert.infrastructure.persistence.entity.ParcoursAcademiqueEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {DossierTransfertMapper.class, UniteEnseignementMapper.class})
public interface ParcoursAcademiqueMapper {
    ParcoursAcademiqueEntity toEntity(ParcoursAcademique domain);
    ParcoursAcademique toDomain(ParcoursAcademiqueEntity entity);
}