package com.app.slink.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.slink.dtos.LoginRequest;
import com.app.slink.entities.User;
import com.app.slink.repositories.UserRepository;
import com.app.slink.security.jwt.JwtAuthenticationResponse;
import com.app.slink.security.jwt.JwtUtils;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public JwtAuthenticationResponse authenticateUser(LoginRequest loginRequest) {
       Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), 
            loginRequest.getPassword()) );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        UserDetailsImpl userDetails =  (UserDetailsImpl) authentication.getPrincipal();
        String jwt =  jwtUtils.generateToken(userDetails);

        return new JwtAuthenticationResponse(jwt);
    }
}
    