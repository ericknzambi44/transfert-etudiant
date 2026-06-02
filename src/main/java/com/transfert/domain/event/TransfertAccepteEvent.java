// domain/event/TransfertAccepteEvent.java
package com.transfert.domain.event;

import com.transfert.domain.model.DossierTransfert;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class TransfertAccepteEvent {
    private final DossierTransfert dossier;
    private final LocalDateTime occuredAt;

    public TransfertAccepteEvent(DossierTransfert dossier) {
        this.dossier = dossier;
        this.occuredAt = LocalDateTime.now();
    }
}