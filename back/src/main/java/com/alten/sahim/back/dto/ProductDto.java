package com.alten.sahim.back.dto;

import com.alten.sahim.back.entity.InventoryStatus;
import com.alten.sahim.back.entity.Product;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "Détail pour un produit")
public class ProductDto {
    @Schema(description = "ID du produit", example = "1")
    private Long id;

    @Schema(description = "Code du produit", example = "P001")
    private String code;

    @Schema(description = "Nom du produit", example = "Produit 1")
    private String name;

    @Schema(description = "Description du produit", example = "Produit 1")
    private String description;
    private String image;
    private String category;

    @Schema(description = "Prix du produit", example = "150.0")
    private Double price;

    @Schema(description = "Quantité en stock", example = "17")
    private Integer quantity;
    private String internalReference;
    private Long shellId;

    @Schema(description = "Statut du stock", example = "INSTOCK")
    private InventoryStatus inventoryStatus;
    private Integer rating;
    private Instant createdAt;
    private Instant updatedAt;

    public static ProductDto toDTO(Product product){
        return ProductDto.builder()
                .id(product.getId())
                .code(product.getCode())
                .name(product.getName())
                .description(product.getDescription())
                .image(product.getImage())
                .category(product.getCategory())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .internalReference(product.getInternalReference())
                .shellId(product.getShellId())
                .inventoryStatus(product.getInventoryStatus())
                .rating(product.getRating())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }

    public static Product toEntity(ProductDto productDto){
        Product product = new Product();
        product.setId(productDto.getId());
        product.setCode(productDto.getCode());
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setImage(productDto.getImage());
        product.setCategory(productDto.getCategory());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        product.setInternalReference(productDto.getInternalReference());
        product.setShellId(productDto.getShellId());
        product.setInventoryStatus(productDto.getInventoryStatus());
        product.setRating(productDto.getRating());
        product.setCreatedAt(productDto.getCreatedAt());
        product.setUpdatedAt(productDto.getUpdatedAt());
        return product;
    }
}
