// infrastructure/persistence/mapper/EtudiantMapper.java
package com.transfert.infrastructure.persistence.mapper;

import com.transfert.domain.model.Etudiant;
import com.transfert.infrastructure.persistence.entity.EtudiantEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EtudiantMapper {
    EtudiantEntity toEntity(Etudiant domain);
    Etudiant toDomain(EtudiantEntity entity);
}