package com.atam.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atam.dto.TokenResponse;
import com.atam.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TokenController {

    private final AuthService authService;

    @GetMapping("/get-token")
    public ResponseEntity<TokenResponse> getToken() {
        TokenResponse tokenResponse = authService.getToken();
        
        if (tokenResponse.getAuthVivelibreToken().isEmpty()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(tokenResponse);
        }

        return ResponseEntity.ok(tokenResponse);
    }
}
