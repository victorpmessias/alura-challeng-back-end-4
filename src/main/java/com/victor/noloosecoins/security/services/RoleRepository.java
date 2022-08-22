package com.victor.noloosecoins.security.services;

import com.victor.noloosecoins.security.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByAuthority(String role);
}
