// infrastructure/security/CustomUserDetailsService.java
package com.transfert.infrastructure.security;

import com.transfert.infrastructure.persistence.entity.EtablissementEntity;
import com.transfert.infrastructure.persistence.repository.SpringDataEtablissementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final SpringDataEtablissementRepository etablissementRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        EtablissementEntity etablissement = etablissementRepository.findByEmailContact(email)
                .orElseThrow(() -> new UsernameNotFoundException("Établissement non trouvé avec email: " + email));
        return new UserPrincipal(
                etablissement.getId(),
                etablissement.getEmailContact(),
                etablissement.getPassword(),
                etablissement.getRole().name(),
                etablissement.getId()
        );
    }

    // Pour l'admin (compte spécial)
    public UserDetails loadAdminByUsername(String username) {
        if ("admin@transfert.com".equals(username)) {
            return new UserPrincipal(UUID.randomUUID(), username, "$2a$10$dummy", "ADMIN", null);
        }
        throw new UsernameNotFoundException("Admin non trouvé");
    }
}