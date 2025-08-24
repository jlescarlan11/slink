package com.app.slink.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.slink.dtos.LoginRequest;
import com.app.slink.dtos.RegisterRequest;
import com.app.slink.entities.User;
import com.app.slink.service.UserService;

import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/public/login")
     public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) { 
        return ResponseEntity.ok(userService.authenticateUser(loginRequest));
     }

    @PostMapping("/public/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {
        
        User user = new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(registerRequest.getPassword());
        user.setRole("ROLE_USER");
        userService.registerUser(user);
         
        return ResponseEntity.ok("User registered successfully");

    }
}
