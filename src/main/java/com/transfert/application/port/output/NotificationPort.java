// application/port/output/NotificationPort.java
package com.transfert.application.port.output;

public interface NotificationPort {
    void notifierEtablissementCible(String message);
    void notifierEtudiant(String email, String sujet, String contenu);
}