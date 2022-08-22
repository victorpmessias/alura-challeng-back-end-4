package com.victor.noloosecoins.controllers;

import com.victor.noloosecoins.security.filters.CustomAuthenticationManager;
import com.victor.noloosecoins.security.models.LoginForm;
import com.victor.noloosecoins.security.models.TokenDto;
import com.victor.noloosecoins.security.services.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    private final CustomAuthenticationManager authManager;
    private final TokenService tokenService;

    public AuthenticationController(CustomAuthenticationManager authManager, TokenService tokenService) {
        this.authManager = authManager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<Object> authenticate(@RequestBody @Valid LoginForm form){

        UsernamePasswordAuthenticationToken loginData = form.convertToAuthToken();

       try {
           Authentication authentication = authManager.authenticate(loginData);
           String token = tokenService.generateToken(authentication);
           return  ResponseEntity.ok(new TokenDto(token, "Bearer"));
       } catch (Exception e) {
           return ResponseEntity.badRequest().body(e.getMessage());
       }
    }

}
