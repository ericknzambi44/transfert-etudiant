package com.transfert.application.dto;

import jakarta.validation.constraints.AssertTrue;
public class ConsentementEtudiantRequest {
    @AssertTrue(message = "Vous devez accepter le transfert")
    private boolean consentement;
    public boolean isConsentement() { return consentement; }
    public void setConsentement(boolean consentement) { this.consentement = consentement; }
}