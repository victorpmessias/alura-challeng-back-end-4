package com.victor.noloosecoins.services;

import com.victor.noloosecoins.exceptions.InvalidEmailException;
import com.victor.noloosecoins.models.user.User;
import com.victor.noloosecoins.models.user.dtos.UserDto;
import com.victor.noloosecoins.models.user.forms.UserForm;
import com.victor.noloosecoins.repositories.UserRepository;
import com.victor.noloosecoins.security.services.CredentialsService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.transaction.Transactional;

@Service
public class UserService {


    private final UserRepository repository;
    private final CredentialsService credentialsService;

    public UserService(UserRepository repository, CredentialsService credentialsService) {
        this.repository = repository;
        this.credentialsService = credentialsService;
    }

    public User getUser(){
        return repository.findById(1L).orElseThrow(EntityExistsException::new);
    }


    @Transactional
    public UserDto registerNewUser(UserForm userForm) {
        checkIfEmailIsAlreadyRegistered(userForm.getEmail());

        User user = userForm.convertToUser();
        user = repository.save(user);
        if(user.getId() != null){
            credentialsService.createCredentials(user.getEmail(), userForm.getPassword());
            return new UserDto(user);
        }
        return null;
    }

    private void checkIfEmailIsAlreadyRegistered(String email){
        if(repository.findByEmail(email).isPresent()){
            throw new InvalidEmailException("Email already been registered");
        }
    }
}
