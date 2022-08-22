package com.victor.noloosecoins.configuration;

import com.victor.noloosecoins.security.filters.AuthenticationFilter;
import com.victor.noloosecoins.security.filters.CustomAuthenticationManager;
import com.victor.noloosecoins.security.services.CredentialsRepository;
import com.victor.noloosecoins.security.services.CredentialsService;
import com.victor.noloosecoins.security.services.TokenService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final TokenService tokenService;
    private final CredentialsRepository credentialsRepository;
    private final CredentialsService credentialsService;
    public SecurityConfig(TokenService tokenService, CredentialsRepository credentialsRepository, CredentialsService credentialsService) {
        this.tokenService = tokenService;
        this.credentialsRepository = credentialsRepository;
        this.credentialsService = credentialsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers(HttpMethod.POST,"/auth").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationManager(new CustomAuthenticationManager(credentialsService))
                .addFilterBefore(new AuthenticationFilter(tokenService, credentialsRepository), UsernamePasswordAuthenticationFilter.class)
        ;
        return http.build();
    }







}
