package com.transfert.infrastructure.notification;

import com.transfert.application.port.output.NotificationPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EmailNotificationAdapter implements NotificationPort {

    @Override
    public void notifierEtablissementCible(String message) {
        log.info("Notification aux établissements cibles: {}", message);
    }

    @Override
    public void notifierEtudiant(String email, String sujet, String contenu) {
        log.info("Notification étudiant {}: {} - {}", email, sujet, contenu);
    }
}