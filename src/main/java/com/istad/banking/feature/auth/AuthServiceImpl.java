package com.istad.banking.feature.auth;


import com.istad.banking.domain.User;
import com.istad.banking.feature.auth.dto.AuthResponse;
import com.istad.banking.feature.auth.dto.ChangePasswordRequest;
import com.istad.banking.feature.auth.dto.LoginRequest;
import com.istad.banking.feature.auth.dto.RefreshTokenRequest;
import com.istad.banking.feature.token.TokenService;
import com.istad.banking.feature.user.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService{
    private final DaoAuthenticationProvider daoAuthenticationProvider;
    private final JwtAuthenticationProvider jwtAuthenticationProvider;
    private final TokenService tokenService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse refresh(RefreshTokenRequest refreshTokenRequest) {

        Authentication auth = new BearerTokenAuthenticationToken(
                refreshTokenRequest.refreshToken()
        );

        auth = jwtAuthenticationProvider.authenticate(auth);

        return tokenService.createToken(auth);
    }

    @Override
    public void changePassword(Jwt jwt, ChangePasswordRequest changePasswordRequest) {
        User user = userRepository.findByPhoneNumber(jwt.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "User has not been found"));
        if(!changePasswordRequest.password().equals(changePasswordRequest.confirmedPassword())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "password does not match");
        }
        if(!passwordEncoder.matches(changePasswordRequest.oldPassword(), user.getPassword())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "old password does not match");
        }
        if(passwordEncoder.matches(changePasswordRequest.password(), user.getPassword())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "New password does not allowed");
        }

        user.setPassword(passwordEncoder.encode(changePasswordRequest.password()));
        userRepository.save(user);
    }

    @Override
    public AuthResponse login(LoginRequest loginRequest) {

        Authentication auth = new UsernamePasswordAuthenticationToken(
                loginRequest.phoneNumber(),
                loginRequest.password()
        );

        auth = daoAuthenticationProvider.authenticate(auth);

        return tokenService.createToken(auth);
    }

}