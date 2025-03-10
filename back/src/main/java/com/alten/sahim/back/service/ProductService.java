package com.alten.sahim.back.service;

import com.alten.sahim.back.dto.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {

    public List<ProductDto> getAllProducts();

    public Optional<ProductDto> getProductById(Long id);

    public ProductDto createProduct(ProductDto productDto);

    public Optional<ProductDto> updateProduct(Long id, ProductDto productDetails);

    public void deleteProduct(Long id);
}