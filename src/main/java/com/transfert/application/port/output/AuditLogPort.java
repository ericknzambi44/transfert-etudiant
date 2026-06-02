// application/port/output/AuditLogPort.java
package com.transfert.application.port.output;

public interface AuditLogPort {
    void log(String action, String utilisateur, String details);
}