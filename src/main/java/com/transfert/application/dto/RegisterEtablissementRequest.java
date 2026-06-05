package com.transfert.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterEtablissementRequest {
    @NotBlank
    private String nom;
    private String adresse;
    @NotBlank @Email
    private String emailContact;
    @NotBlank
    private String password;
    
}