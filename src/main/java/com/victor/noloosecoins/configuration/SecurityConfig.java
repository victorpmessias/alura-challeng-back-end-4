package com.victor.noloosecoins.configuration;

import com.victor.noloosecoins.security.filters.AuthenticationFilter;
import com.victor.noloosecoins.security.filters.CustomAuthenticationManager;
import com.victor.noloosecoins.security.services.CredentialsRepository;
import com.victor.noloosecoins.security.services.CredentialsService;
import com.victor.noloosecoins.security.services.TokenService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@Profile(value = "prod")
public class SecurityConfig {

    private final TokenService tokenService;
    private final CredentialsRepository credentialsRepository;
    private final CredentialsService credentialsService;

    private static final String[] AUTH_WHITELIST = {
            "/authenticate",
            "/swagger-resources/**",
            "/swagger-ui/**",
            "/v3/api-docs",
            "/webjars/**"
    };

    public SecurityConfig(TokenService tokenService, CredentialsRepository credentialsRepository, CredentialsService credentialsService) {
        this.tokenService = tokenService;
        this.credentialsRepository = credentialsRepository;
        this.credentialsService = credentialsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers(AUTH_WHITELIST).permitAll()
                .antMatchers(HttpMethod.POST, "/auth").permitAll()
                .antMatchers(HttpMethod.POST, "/users/register").permitAll()
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
