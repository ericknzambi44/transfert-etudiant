// application/mapper/ParcoursMapper.java
package com.transfert.application.mapper;

import com.transfert.application.dto.ParcoursResponse;
import com.transfert.domain.model.ParcoursAcademique;
import com.transfert.domain.model.UniteEnseignement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ParcoursMapper {
    @Mapping(target = "unites", expression = "java(toUniteResponses(parcours.getUnitesValidees()))")
    ParcoursResponse toResponse(ParcoursAcademique parcours);

    default List<ParcoursResponse.UniteResponse> toUniteResponses(List<UniteEnseignement> unites) {
        return unites.stream().map(ue -> {
            ParcoursResponse.UniteResponse resp = new ParcoursResponse.UniteResponse();
            resp.setCode(ue.getCode());
            resp.setNom(ue.getNom());
            resp.setCredits(ue.getCredits());
            resp.setNote(ue.getNote());
            return resp;
        }).collect(java.util.stream.Collectors.toList());
    }
}