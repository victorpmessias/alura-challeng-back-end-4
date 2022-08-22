package com.victor.noloosecoins.services;

import com.victor.noloosecoins.models.user.User;
import com.victor.noloosecoins.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;

@Service
public class UserService {


    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User getUser(){
        return repository.findById(1L).orElseThrow(EntityExistsException::new);
    }
}
