package com.transfert.infrastructure.security;

import com.transfert.infrastructure.persistence.entity.EtablissementEntity;
import com.transfert.infrastructure.persistence.entity.EtudiantEntity;
import com.transfert.infrastructure.persistence.repository.SpringDataEtablissementRepository;
import com.transfert.infrastructure.persistence.repository.SpringDataEtudiantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final SpringDataEtablissementRepository etablissementRepository;
    private final SpringDataEtudiantRepository etudiantRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Établissement
        var etablissementOpt = etablissementRepository.findByEmailContact(email);
        if (etablissementOpt.isPresent()) {
            EtablissementEntity e = etablissementOpt.get();
            return new UserPrincipal(
                e.getId(),
                e.getEmailContact(),
                e.getPassword(),
                "ETABLISSEMENT",
                e.getId(),
                "ETABLISSEMENT"
            );
        }

        // Étudiant
        var etudiantOpt = etudiantRepository.findByEmail(email);
        if (etudiantOpt.isPresent()) {
            EtudiantEntity et = etudiantOpt.get();
            return new UserPrincipal(
                et.getId(),
                et.getEmail(),
                et.getPassword(),
                "ETUDIANT",
                null,
                "ETUDIANT"
            );
        }

        throw new UsernameNotFoundException("Utilisateur non trouvé avec l'email: " + email);
    }
}