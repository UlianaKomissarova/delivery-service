package com.example.delivery_service.delivery_position.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.*;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Schema(description = "Сущность позиции поставки")
public class PositionDto {
    private Long id;
    @NotNull
    private Long deliveryId;
    @NotNull
    private Long productId;
    @Positive
    private BigDecimal price;
    @Positive
    private int quantity;
}