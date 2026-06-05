package com.transfert.application.mapper;

import com.transfert.application.dto.ParcoursResponse;
import com.transfert.domain.model.ParcoursAcademique;
import com.transfert.domain.model.UniteEnseignement;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ParcoursMapper {

    public ParcoursResponse toResponse(ParcoursAcademique parcours) {
        if (parcours == null) return null;
        ParcoursResponse response = new ParcoursResponse();
        response.setIntituleDiplome(parcours.getIntituleDiplome());
        response.setCreditsObtenus(parcours.getCreditsObtenus());
        List<ParcoursResponse.UniteResponse> unites = parcours.getUnitesValidees().stream()
                .map(this::toUniteResponse)
                .collect(Collectors.toList());
        response.setUnites(unites);
        return response;
    }

    private ParcoursResponse.UniteResponse toUniteResponse(UniteEnseignement ue) {
        ParcoursResponse.UniteResponse resp = new ParcoursResponse.UniteResponse();
        resp.setCode(ue.getCode());
        resp.setNom(ue.getNom());
        resp.setCredits(ue.getCredits());
        resp.setNote(ue.getNote());
        return resp;
    }
}