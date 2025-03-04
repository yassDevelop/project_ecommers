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
    public ResponseEntity<UserDto> register(
            @Parameter(description = "Détail de l'utilisateur à sauvegarder", required = true)
            @RequestBody UserDto userDto) {
            return ResponseEntity.ok().body(authService.registerUser(userDto));
    }

    @PostMapping("/login")
    @Operation(summary = "Authentification pou un utilisateur", description = "Utilisateur permet de se connecter et de recevoir un token JWT.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Connexion réussie, token JWT retourné"),
            @ApiResponse(responseCode = "401", description = "Email ou mot de passe incorrect")
    })
    public ResponseEntity<ResponseDto> login(
            @Parameter(description = "Informations de l'authentification (email et mot de passe)", required = true)
            @RequestBody RequestDto request) {
        String token = authService.authenticate(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(ResponseDto.toDto(token));
    }
}