// application/usecase/ConsulterParcoursUseCase.java
package com.transfert.application.usecase;

import com.transfert.domain.model.ParcoursAcademique;
import com.transfert.domain.repository.ParcoursAcademiqueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConsulterParcoursUseCase {
    private final ParcoursAcademiqueRepository parcoursRepository;

    public ParcoursAcademique execute(UUID dossierId) {
        return parcoursRepository.findByDossierId(dossierId)
                .orElseThrow(() -> new IllegalArgumentException("Parcours non trouvé pour ce dossier"));
    }
}