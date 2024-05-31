package com.example.delivery_service.product.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Schema(description = "Сущность продукции")
public class ProductDto {
    private Long id;
    @NotBlank
    private String type;
}