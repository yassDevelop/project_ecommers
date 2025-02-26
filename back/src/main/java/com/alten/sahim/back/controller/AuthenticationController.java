package com.alten.sahim.back.controller;

import com.alten.sahim.back.dto.*;
import com.alten.sahim.back.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication API", description = "API pour l'authentification des utilisateurs")
public class AuthenticationController {
    private final AuthService authService;

    public AuthenticationController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    @Operation(summary = "Sauvegarder un nouvel utilisateur", description = "Permet d'enregistrer un nouvel utilisateur")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Utilisateur enregistré avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide")
    })
    public ResponseEntity<String> register(
            @Parameter(description = "Détail de l'utilisateur à sauvegarder", required = true)
            @RequestBody UserDto userDto) {
            authService.registerUser(userDto);
            return ResponseEntity.ok("Utilisateur enregistré avec succès !");
    }

    @PostMapping("/login")
    @Operation(summary = "Authentification pou un utilisateur", description = "Utilisateur permet de se connecter et de recevoir un token JWT.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Connexion réussie, token JWT retourné"),
            @ApiResponse(responseCode = "401", description = "Email ou mot de passe incorrect")
    })
    public ResponseEntity<Map<String, String>> login(
            @Parameter(description = "Informations de l'authentification (email et mot de passe)", required = true)
            @RequestBody Map<String, String> credentials) {
        String token = authService.authenticate(credentials.get("email"), credentials.get("password"));
        return ResponseEntity.ok(Map.of("token", token));
    }
}