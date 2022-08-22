package com.victor.noloosecoins.security.services;

import com.victor.noloosecoins.security.models.Credentials;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CredentialsService implements UserDetailsService {

    private final CredentialsRepository repository;

    public CredentialsService(CredentialsRepository repository) {
        this.repository = repository;
    }

    @Override
    public Credentials loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username);
    }


}
