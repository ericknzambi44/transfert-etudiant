// application/dto/AcceptationCibleRequest.java
package com.transfert.application.dto;

import lombok.Data;

@Data
public class AcceptationCibleRequest {
    private String commentaire;
    private boolean accepte; // true = accepter, false = refuser
}