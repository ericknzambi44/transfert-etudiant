// infrastructure/persistence/mapper/EtablissementMapper.java
package com.transfert.infrastructure.persistence.mapper;

import com.transfert.domain.model.Etablissement;
import com.transfert.infrastructure.persistence.entity.EtablissementEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EtablissementMapper {
    @Mapping(target = "password", ignore = true) // le domaine n'a pas de mot de passe, on gère à part
    EtablissementEntity toEntity(Etablissement domain);
    Etablissement toDomain(EtablissementEntity entity);
}