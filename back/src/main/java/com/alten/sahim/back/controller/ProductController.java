package com.alten.sahim.back.controller;

import com.alten.sahim.back.dto.ProductDto;
import com.alten.sahim.back.service.AuthService;
import com.alten.sahim.back.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Tag(name = "Products API", description = "API pour la gestion des produits")
public class ProductController {
    private final ProductService productService;
    private final AuthService authService;

    @GetMapping
    @Operation(summary = "Récupérer tous les produits", description = "Renvoie une liste de tous les produits disponibles.")
    @ApiResponse(responseCode = "200", description = "Liste des produits récupérée avec succès")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer un produit par ID", description = "Renvoie le détail pour un produit spécifique.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produit trouvé"),
            @ApiResponse(responseCode = "404", description = "Produit non trouvé")
    })
    public ResponseEntity<ProductDto> getProductById(
            @Parameter(description = "ID du produit à récupérer", required = true)
            @PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductById(id).orElseThrow());
    }

    @PostMapping
    @Operation(summary = "Créer un nouveau produit", description = "Créer un nouveau produit. Réservation à l'admin.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Produit créé avec succès"),
            @ApiResponse(responseCode = "403", description = "Accès refusé")
    })
    public ResponseEntity<ProductDto> createProduct(
            @Parameter(description = "Détail du produit à créer", required = true)
            @RequestBody ProductDto productDto) {
        authService.isAdmin();
        return ResponseEntity.ok(productService.createProduct(productDto));
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Mettre à jour un produit", description = "Met à jour les détails d'un produit existant. Réservé à l'admin.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "La mis à jour du Produit a été fait avec succès"),
            @ApiResponse(responseCode = "404", description = "Produit non trouvé"),
            @ApiResponse(responseCode = "403", description = "Accès refusé")
    })
    public ResponseEntity<ProductDto> updateProduct(
            @Parameter(description = "Mis àjour de l'ID du produit", required = true)
            @PathVariable Long id,
            @Parameter(description = "Nouveau détail du produit", required = true)
            @RequestBody ProductDto productDto) {
        authService.isAdmin();
        return ResponseEntity.ok(productService.updateProduct(id, productDto).orElseThrow());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Supprimer un produit", description = "Supprimee un produit existant. Réservation à l'admin.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Produit supprimé avec succès"),
            @ApiResponse(responseCode = "403", description = "Accès refusé")
    })
    public ResponseEntity<Void> deleteProduct(
            @Parameter(description = "ID du produit à supprimer", required = true)
            @PathVariable Long id) {
        authService.isAdmin();
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}