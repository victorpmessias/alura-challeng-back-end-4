package com.victor.noloosecoins.security.services;

import com.victor.noloosecoins.security.models.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CredentialsRepository extends JpaRepository<Credentials, Long> {

    Optional<Credentials> findByUsername(String username);
}
