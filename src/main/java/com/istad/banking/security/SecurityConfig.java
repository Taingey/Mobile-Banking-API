package com.istad.banking.security;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
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
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.util.UUID;

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
//        httpSecurity.httpBasic(Customizer.withDefaults());

        httpSecurity.oauth2ResourceServer(jwt -> jwt.jwt(Customizer.withDefaults()));

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
    @Bean
    KeyPair keyPair(){
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            return keyPairGenerator.generateKeyPair();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    RSAKey rsaKey(KeyPair keyPair){
        return new RSAKey.Builder((RSAPublicKey) keyPair.getPublic())
                .privateKey(keyPair.getPrivate())
                .keyID(UUID.randomUUID().toString())
                .build();
    }
    @Bean
    JWKSource<SecurityContext> jwkSource(RSAKey rsaKey){
        JWKSet jwkSet = new JWKSet(rsaKey);
        return ((jwkSelector, securityContext) -> jwkSelector
                .select(jwkSet));
    }
    @Bean
    JwtEncoder jwtEncoder(JWKSource<SecurityContext> jwkSource){
        return new NimbusJwtEncoder(jwkSource);
    }

    @Bean
     JwtDecoder jwtDecoder(RSAKey rsaKey) throws JOSEException{
        return NimbusJwtDecoder
                .withPublicKey(rsaKey.toRSAPublicKey())
                .build();
    }

}