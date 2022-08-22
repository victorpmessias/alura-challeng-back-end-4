package com.victor.noloosecoins.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.victor.noloosecoins.security.models.CredentialsDto;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class AuthenticationFilter extends OncePerRequestFilter {






    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {



    }


    private String getToken(HttpServletRequest req){
        String token = req.getHeader("Authorization");
        if(token == null || !token.startsWith("Bearer")){
            return null;
        }
        return token.substring(7, token.length());
    }
}
