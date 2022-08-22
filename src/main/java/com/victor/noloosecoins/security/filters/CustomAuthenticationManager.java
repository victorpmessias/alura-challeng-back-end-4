package com.victor.noloosecoins.security.filters;

import com.victor.noloosecoins.security.models.Credentials;
import com.victor.noloosecoins.security.services.CredentialsService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomAuthenticationManager implements AuthenticationManager {

    private final CredentialsService credentialsService;

    public CustomAuthenticationManager(CredentialsService credentialsService) {
        this.credentialsService = credentialsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException{


        Credentials userRegistry = credentialsService.loadUserByUsername( authentication.getPrincipal().toString());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();



        if (userRegistry.getUsername().equals(authentication.getPrincipal().toString()) && encoder.matches(authentication.getCredentials().toString(), userRegistry.getPassword())) {
            return new UsernamePasswordAuthenticationToken(userRegistry, null, userRegistry.getAuthorities());
        }else{
            throw new AuthenticationException("Bad Credentials");
        }
    }

    private static class AuthenticationException extends org.springframework.security.core.AuthenticationException{

        public AuthenticationException(String msg, Throwable cause) {
            super(msg, cause);
        }

        public AuthenticationException(String msg) {
            super(msg);
        }
    }
}
