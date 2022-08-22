package com.victor.noloosecoins.controllers;

import com.victor.noloosecoins.security.models.Credentials;
import com.victor.noloosecoins.security.services.CredentialsService;
import com.victor.noloosecoins.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService service;
    private final CredentialsService detailsService;

    public UserController(UserService service, CredentialsService detailsService) {
        this.service = service;
        this.detailsService = detailsService;
    }

    @GetMapping
    public Credentials getUser(){
        return detailsService.loadUserByUsername("email@email.com");
    }
}
