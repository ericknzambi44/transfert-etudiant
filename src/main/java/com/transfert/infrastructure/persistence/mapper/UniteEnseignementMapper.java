// infrastructure/persistence/mapper/UniteEnseignementMapper.java
package com.transfert.infrastructure.persistence.mapper;

import com.transfert.domain.model.UniteEnseignement;
import com.transfert.infrastructure.persistence.entity.UniteEnseignementEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UniteEnseignementMapper {
    UniteEnseignementEntity toEntity(UniteEnseignement domain);
    UniteEnseignement toDomain(UniteEnseignementEntity entity);
}