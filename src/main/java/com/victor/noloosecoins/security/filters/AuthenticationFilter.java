package com.victor.noloosecoins.security.filters;

import com.victor.noloosecoins.security.models.Credentials;
import com.victor.noloosecoins.security.services.CredentialsRepository;
import com.victor.noloosecoins.security.services.TokenService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class AuthenticationFilter extends OncePerRequestFilter {



    private final TokenService tokenService;
    private final CredentialsRepository credentialsRepository;
    public AuthenticationFilter(TokenService tokenService, CredentialsRepository credentialsRepository) {
        this.tokenService = tokenService;
        this.credentialsRepository = credentialsRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getToken(request);
        if(tokenService.isTokenValid(token)){
            authenticate(token);
        }
        filterChain.doFilter(request,response);
    }

    private void authenticate(String token){
        Long credentialId = tokenService.getLoggedUser(token);
        Optional<Credentials> userCredentialsOpt = credentialsRepository.findById(credentialId);
        if(userCredentialsOpt.isPresent()){
            Credentials userCredentials = userCredentialsOpt.get();
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userCredentials, null, userCredentials.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
    }
    private String getToken(HttpServletRequest req){
        String token = req.getHeader("Authorization");
        if(token == null || !token.startsWith("Bearer")){
            return null;
        }
        return token.substring(7);
    }
}
