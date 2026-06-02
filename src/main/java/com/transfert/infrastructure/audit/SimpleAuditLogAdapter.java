package com.transfert.infrastructure.audit;

import com.transfert.application.port.output.AuditLogPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SimpleAuditLogAdapter implements AuditLogPort {
    @Override
    public void log(String action, String utilisateur, String details) {
        log.info("AUDIT - Action: {}, Utilisateur: {}, Détails: {}", action, utilisateur, details);
    }
}