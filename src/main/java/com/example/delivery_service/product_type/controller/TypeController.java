package com.example.delivery_service.product_type.controller;

import com.example.delivery_service.product_type.model.ProductType;
import com.example.delivery_service.product_type.service.TypeServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/types")
@RequiredArgsConstructor
@Slf4j
@Tag(name="Типы продукции", description="Управление типами продукции")
public class TypeController {
    private final TypeServiceInterface service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
        summary = "Создание нового вида продукции",
        description = "Позволяет добавить новый вид продукции"
    )
    public ProductType save(@RequestParam @NotBlank String name) {
        log.info("Добавление нового вида продукта " + name);

        return service.save(name);
    }
}