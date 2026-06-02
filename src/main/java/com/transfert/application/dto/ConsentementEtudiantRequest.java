// application/dto/ConsentementEtudiantRequest.java
package com.transfert.application.dto;

import jakarta.validation.constraints.AssertTrue;
import lombok.Data;

@Data
public class ConsentementEtudiantRequest {
    @AssertTrue(message = "Vous devez accepter le transfert")
    private boolean consentement;
}