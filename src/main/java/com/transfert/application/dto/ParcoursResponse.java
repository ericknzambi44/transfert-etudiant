package com.transfert.application.dto;

import lombok.Data;
import java.util.List;

@Data
public class ParcoursResponse {
    private String intituleDiplome;
    private int creditsObtenus;
    private List<UniteResponse> unites;

    @Data
    public static class UniteResponse {
        private String code;
        private String nom;
        private int credits;
        private Double note;
    }
}