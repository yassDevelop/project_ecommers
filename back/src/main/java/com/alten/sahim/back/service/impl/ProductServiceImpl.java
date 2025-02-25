package com.alten.sahim.back.service.impl;

import com.alten.sahim.back.dao.ProductDao;
import com.alten.sahim.back.dto.ProductDto;
import com.alten.sahim.back.entity.Product;
import com.alten.sahim.back.exception.ProductNotFoundException;
import com.alten.sahim.back.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    public List<ProductDto> getAllProducts() {
        return productDao.findAll().stream()
                .map(ProductDto::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<ProductDto> getProductById(Long id) {
        Product product = productDao.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Produit introuvable avec ID: " + id));
        return Optional.ofNullable(ProductDto.toDTO(product));
    }

    public ProductDto createProduct(ProductDto productDTO) {
        Product product = ProductDto.toEntity(productDTO);
        return ProductDto.toDTO(productDao.save(product));
    }

    @Transactional
    public Optional<ProductDto> updateProduct(Long id, ProductDto productDTO) {
        return Optional.ofNullable(productDao.findById(id)
                .map(existingProduct -> {
                    if (productDTO.getName() != null) existingProduct.setName(productDTO.getName());
                    if (productDTO.getDescription() != null)
                        existingProduct.setDescription(productDTO.getDescription());
                    if (productDTO.getPrice() != null) existingProduct.setPrice(productDTO.getPrice());
                    if (productDTO.getQuantity() != null) existingProduct.setQuantity(productDTO.getQuantity());
                    if (productDTO.getCategory() != null) existingProduct.setCategory(productDTO.getCategory());
                    if (productDTO.getImage() != null) existingProduct.setImage(productDTO.getImage());
                    if (productDTO.getInventoryStatus() != null)
                        existingProduct.setInventoryStatus(productDTO.getInventoryStatus());
                    if (productDTO.getRating() != null) existingProduct.setRating(productDTO.getRating());

                    existingProduct.setUpdatedAt(Instant.ofEpochSecond(System.currentTimeMillis()));

                    return ProductDto.toDTO(productDao.save(existingProduct));
                })
                .orElseThrow(() -> new ProductNotFoundException("Produit introuvable avec ID: " + id)));
    }


    public void deleteProduct(Long id) {
        if (!productDao.existsById(id)) {
            throw new ProductNotFoundException("Produit introuvable avec ID: " + id);
        }
        productDao.deleteById(id);
    }
}