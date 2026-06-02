// infrastructure/notification/EmailNotificationAdapter.java
package com.transfert.infrastructure.notification;

import com.transfert.application.port.output.NotificationPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmailNotificationAdapter implements NotificationPort {
    private final JavaMailSender mailSender;

    @Override
    public void notifierEtablissementCible(String message) {
        // Dans un vrai cas, on enverrait un email à tous les établissements cibles ou à un admin
        log.info("Notification aux établissements cibles: {}", message);
        // Exemple d'envoi d'email:
        // SimpleMailMessage msg = new SimpleMailMessage();
        // msg.setTo("cible@example.com");
        // msg.setSubject("Nouveau transfert");
        // msg.setText(message);
        // mailSender.send(msg);
    }

    @Override
    public void notifierEtudiant(String email, String sujet, String contenu) {
        log.info("Notification étudiant {}: {} - {}", email, sujet, contenu);
    }
}