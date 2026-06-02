package com.transfert.application.mapper;

import com.transfert.application.dto.CreationDossierRequest;
import com.transfert.application.dto.DossierResponse;
import com.transfert.domain.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface DossierMapper {
    DossierMapper INSTANCE = Mappers.getMapper(DossierMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dateNaissance", expression = "java(java.time.LocalDate.parse(request.getDateNaissance()))")
    Etudiant toEtudiant(CreationDossierRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dossier", source = "dossier")
    @Mapping(target = "unitesValidees", expression = "java(toUniteEnseignements(request.getUnites()))")
    ParcoursAcademique toParcours(DossierTransfert dossier, CreationDossierRequest request);

    default List<UniteEnseignement> toUniteEnseignements(List<CreationDossierRequest.UniteDTO> dtos) {
        if (dtos == null) return List.of();
        return dtos.stream()
                .map(dto -> new UniteEnseignement(
                        dto.getCode(),
                        dto.getNom(),
                        dto.getCredits(),
                        dto.getNote()
                ))
                .collect(Collectors.toList());
    }

    DossierResponse toResponse(DossierTransfert dossier);
}