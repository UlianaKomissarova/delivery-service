package com.example.delivery_service.product.service;

import com.example.delivery_service.core.exception.exceptions.ProductNotFoundException;
import com.example.delivery_service.product.dto.ProductDto;
import com.example.delivery_service.product.model.Product;
import com.example.delivery_service.product.repository.ProductRepository;
import com.example.delivery_service.product_type.service.TypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductService implements ProductServiceInterface {
    private final ProductRepository repository;
    private final TypeService typeService;

    @Override
    @Transactional
    public ProductDto save(ProductDto dto) {
        Product product = new Product();
        product.setType(typeService.getTypeByName(dto.getType()));

        product = repository.save(product);

        return new ProductDto(product.getId(), product.getType().getName());
    }

    public Product getExistingProductById(Long id) {
        return repository.findById(id).orElseThrow(
            () -> new ProductNotFoundException("Товар с id " + id + " не найден.")
        );
    }
}