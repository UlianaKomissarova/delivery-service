package com.example.delivery_service.delivery_position.controller;

import com.example.delivery_service.delivery_position.dto.PositionDto;
import com.example.delivery_service.delivery_position.service.PositionServiceInterface;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/positions")
@RequiredArgsConstructor
@Slf4j
@Tag(name="Позиции поставки", description="Управление позициями одной поставки")
public class PositionController {
    private final PositionServiceInterface service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
        summary = "Добавление позиции поставки",
        description = "Позволяет добавить/сохранить позицию одной поставки"
    )
    public PositionDto save(@RequestBody @Valid PositionDto dto) {
        log.info("Добавление новой позиции поставки " + dto.getDeliveryId() + " с продуктом " + dto.getProductId());

        return service.save(dto);
    }
}