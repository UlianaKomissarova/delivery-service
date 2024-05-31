package com.example.delivery_service.product.controller;

import com.example.delivery_service.product.dto.ProductDto;
import com.example.delivery_service.product.service.ProductServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Slf4j
@Tag(name="Продукция", description="Управление продукцией")
public class ProductController {
    private final ProductServiceInterface service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
        summary = "Добавление продукции",
        description = "Позволяет добавить продукцию"
    )
    public ProductDto save(@RequestBody @Valid ProductDto dto) {
        log.info("Добавление нового продукта типа " + dto.getType());

        return service.save(dto);
    }
}