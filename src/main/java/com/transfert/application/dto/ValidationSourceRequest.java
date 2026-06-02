// application/dto/ValidationSourceRequest.java
package com.transfert.application.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ValidationSourceRequest {
    @NotBlank
    private String commentaire;
}