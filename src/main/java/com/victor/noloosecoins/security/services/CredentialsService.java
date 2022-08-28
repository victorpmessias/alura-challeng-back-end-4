package com.victor.noloosecoins.security.services;

import com.victor.noloosecoins.security.models.Credentials;
import com.victor.noloosecoins.security.models.Role;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CredentialsService implements UserDetailsService {

    private final CredentialsRepository repository;
    private final RoleRepository roleRepository;

    public CredentialsService(CredentialsRepository repository, RoleRepository roleRepository) {
        this.repository = repository;
        this.roleRepository = roleRepository;
    }

    @Override
    public Credentials loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Check your credentiasl"));
    }


    public Credentials createCredentials(String email, String password) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        Credentials credentials = new Credentials();
        Role role = roleRepository.findByAuthority("ROLE_USER");
        credentials.setUsername(email);
        credentials.setPassword(encoder.encode(password));
        credentials.setRoles(List.of(role));

        return repository.save(credentials);
    }
}
