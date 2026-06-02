// domain/event/TransfertCreeEvent.java
package com.transfert.domain.event;

import com.transfert.domain.model.DossierTransfert;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class TransfertCreeEvent {
    private final DossierTransfert dossier;
    private final LocalDateTime occuredAt;

    public TransfertCreeEvent(DossierTransfert dossier) {
        this.dossier = dossier;
        this.occuredAt = LocalDateTime.now();
    }
}