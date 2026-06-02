// application/dto/RechercheEtudiantRequest.java
package com.transfert.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RechercheEtudiantRequest {
    @NotBlank @Email
    private String email;
}