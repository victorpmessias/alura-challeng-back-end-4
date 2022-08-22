package com.victor.noloosecoins.security.services;

import com.victor.noloosecoins.security.models.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CredentialsRepository extends JpaRepository<Credentials, Long> {

    Credentials findByUsername(String username);
}
