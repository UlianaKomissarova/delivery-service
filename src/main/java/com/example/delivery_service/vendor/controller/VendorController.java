package com.example.delivery_service.vendor.controller;

import com.example.delivery_service.vendor.dto.VendorDto;
import com.example.delivery_service.vendor.service.VendorServiceInterface;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/vendors")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Поставщики", description = "Взаимодействие с поставщиками")
public class VendorController {
    private final VendorServiceInterface service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
        summary = "Регистрация поставщика",
        description = "Позволяет зарегистрировать поставщика"
    )
    public VendorDto save(@RequestBody @Valid VendorDto dto) {
        log.info("Добавление нового поставщика " + dto.getName());

        return service.save(dto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(
        summary = "Получение списка всех поставщиков",
        description = "Позволяет получить список всех поставщиков"
    )
    public Collection<VendorDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
        summary = "Получение поставщика",
        description = "Позволяет получить информацию о поставщике"
    )
    public VendorDto findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
        summary = "Удаление поставщика",
        description = "Позволяет удалить поставщика"
    )
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(
        summary = "Обновление данных поставщика",
        description = "Позволяет обновить имя/поставки поставщика")
    public VendorDto update(
        @PathVariable @Parameter(description = "Идентификатор поставщика") Long id,
        @RequestBody @Valid VendorDto dto
    ) {
        log.info("Редактирование данных поставщика");

        return service.update(id, dto);
    }
}