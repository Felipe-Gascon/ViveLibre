package com.example.atam.service;

import java.time.LocalDate;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import dto.TokenResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final RestTemplate restTemplate;
    private final String authServiceUrl = "http://localhost:8080/token"; // URL del servicio de autenticación
    private final String username = "auth-vivelibre"; // Usuario para el servicio de autenticación
    private final String password = "password"; // Contraseña para el servicio de autenticación

    public TokenResponse getToken() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String requestJson = "{\"username\":\"" + this.username + "\",\"password\":\"" + this.password + "\"}";

        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);
        try {
            ResponseEntity<String> response = restTemplate.exchange(this.authServiceUrl, HttpMethod.POST, entity, String.class);
            
            if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
                JSONObject jsonResponse = new JSONObject(response.getBody());
                String token = jsonResponse.optString("auth-vivelibre-token", ""); // Asumiendo que la respuesta contiene este campo
                return new TokenResponse(token, LocalDate.now().toString()); // Asumiendo que quieres la fecha actual
            } else {
                return new TokenResponse("", "");
            }
        } catch (HttpClientErrorException e) {
            // Log and handle the error appropriately
            return new TokenResponse("", "");
        }
    }
}
