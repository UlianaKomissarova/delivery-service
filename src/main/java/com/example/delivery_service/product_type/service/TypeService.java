package com.example.delivery_service.product_type.service;

import com.example.delivery_service.product_type.model.ProductType;
import com.example.delivery_service.product_type.repository.TypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TypeService implements TypeServiceInterface {
    private final TypeRepository typeRepository;

    @Override
    @Transactional
    public ProductType save(String name) {
        ProductType type = ProductType.builder()
            .name(name)
            .build();

        return typeRepository.save(type);
    }

    public ProductType getTypeByName(String name) {
        if (typeRepository.findByName(name) != null) {
            return typeRepository.findByName(name);
        } else {
            return save(name);
        }
    }
}