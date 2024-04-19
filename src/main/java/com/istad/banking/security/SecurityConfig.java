package com.istad.banking.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    @Bean
    InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        UserDetails userAdmin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .roles("USER", "ADMIN")
                .build();

        UserDetails userEditor = User.builder()
                .username("editor")
                .password(passwordEncoder.encode("editor"))
                .roles("USER", "EDITOR")
                .build();

        UserDetails userStaff = User.builder()
                .username("staff")
                .password(passwordEncoder.encode("staff"))
                .roles("USER", "STAFF")
                .build();

        UserDetails userCustomer = User.builder()
                .username("customer")
                .password(passwordEncoder.encode("customer"))
                .roles("USER", "CUSTOMER")
                .build();

        manager.createUser(userAdmin);
        manager.createUser(userEditor);
        manager.createUser(userStaff);
        manager.createUser(userCustomer);
        return manager;
    }
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeHttpRequests(request -> request
                .requestMatchers(HttpMethod.POST, "/api/v1/users/**").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/api/v1/users/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT,"/api/v1/users/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.GET,"/api/v1/users/**").hasAnyRole("ADMIN", "EDITOR")
                .anyRequest()
                .authenticated()
        );
        httpSecurity.httpBasic(Customizer.withDefaults());

        httpSecurity.csrf(token -> token.disable());
        httpSecurity.sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return httpSecurity.build();
    }

    @Bean
    DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);

        return provider;
    }
}