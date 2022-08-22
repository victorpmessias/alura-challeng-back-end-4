package com.victor.noloosecoins.repositories;

import com.victor.noloosecoins.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
