package com.skylimit.Skylimit.controller;

import com.skylimit.Skylimit.dto.loginRequest.LoginRequest;
import com.skylimit.Skylimit.service.JwtService;

import lombok.AllArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class AuthController {

    private AuthenticationManager authenticationManager;

    private JwtService jwtService;

    @PostMapping("/login")
    public String login(
            @RequestBody LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        return jwtService.generateToken(
                request.getUsername()
        );
    }
}