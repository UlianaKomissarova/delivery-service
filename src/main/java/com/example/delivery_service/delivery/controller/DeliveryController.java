package com.example.delivery_service.delivery.controller;

import com.example.delivery_service.delivery.dto.DeliveryDto;
import com.example.delivery_service.delivery.model.Delivery;
import com.example.delivery_service.delivery.service.DeliveryServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/deliveries")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Поставки", description = "Управление поставками")
public class DeliveryController {
    private final DeliveryServiceInterface service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
        summary = "Создание поставки",
        description = "Позволяет создать поставку"
    )
    public DeliveryDto save(@RequestBody @Valid DeliveryDto dto) {
        log.info("Добавление новой поставки от поставщика " + dto.getVendorId());

        return service.save(dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
        summary = "Удаление поставки",
        description = "Позволяет удалить поставку"
    )
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(
        summary = "Получение списка всех поставок",
        description = "Позволяет получить список всех поставок"
    )
    public List<Delivery> findAll() {
        return service.findAll();
    }
}